package fr.epita.iamtesting.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iamtesting.datamodel.Identity;
import fr.epita.iamtesting.services.HibernateDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestHibernate {

	private static final Logger LOGGER = LogManager.getLogger(TestHibernate.class); 
	
	@Inject
	DataSource ds;
	
	@Inject
	HibernateDAO dao;
	
	@Before
	public void setListIdentity(){
		List<Identity> list = new ArrayList<Identity>();
		list.add(new Identity(10,"zick","zick100",new Date()));
		list.add(new Identity(9,"zick","zick6",new Date()));
		list.add(new Identity(8,"zick","zick5",new Date()));
		list.add(new Identity(7,"zick","zick4",new Date()));
		list.add(new Identity(2,"sylnia","zick3",new Date()));
		list.add(new Identity(1,"sylnia2","zick33",new Date()));
		
		for(int i=0;i<list.size();i++){
			dao.saveOrUpdate(list.get(i));
		}
		
		LOGGER.info("create database");
	}
	
	@Test
	public void testCreate() throws SQLException{
		LOGGER.info("beginning create test");
		//GIVEN
		Identity i = new Identity(0,"a","a",new Date());
		
		//WHEN
		dao.saveOrUpdate(i);
		
		//THEN
		Connection c = ds.getConnection();
		PreparedStatement selectAll = c.prepareStatement("SELECT * from IDENTITIES" );
		ResultSet results = selectAll.executeQuery();
		
		int count = 0;
		while(results.next()){
			count++;
		}
		
		Assert.assertEquals(7, count);
		LOGGER.info("create test succesfull");
	}
	
	@Test
	public void testUpdateHibernateDAO() throws ParseException, SQLException{
		LOGGER.info("beginning update test");
		// GIVEN
		Identity identity = new Identity(0, "tbr", "tbr@tbr.com", new Date());

		// WHEN
		dao.saveOrUpdate(identity);
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		identity.setBirthdate(sdf.parse("09/04/1986"));
		dao.saveOrUpdate(identity);

		// THEN
		final Connection connection = ds.getConnection();
		final PreparedStatement prepareStatement = connection
				.prepareStatement("SELECT BIRTHDATE FROM IDENTITIES WHERE IDENTITIES.EMAIL='tbr@tbr.com'");
		final ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		final Date date = rs.getDate(1);
		Assert.assertEquals("09/04/1986", sdf.format(date));
		LOGGER.info("got this modified {}", date);
		LOGGER.info("update test successful");
		
	}
	
	@Test
	public void testSearchHibernateDAO() throws SQLException{
		LOGGER.info("beginning search test");
		
		//GIVEN
		 Identity identity = new Identity(0, "zick", "tbr@tbr.com", new Date());
		
		//WHEN
		List<Identity> result = dao.search(identity);		
				
		//THEN
		Connection c = ds.getConnection();
		PreparedStatement selectAll = c.prepareStatement("SELECT * from IDENTITIES WHERE IDENTITIES.NAME='" + identity.getDisplayName() + "'" );
		ResultSet results = selectAll.executeQuery();
			
		int count = 0;
		while(results.next()){
			count++;
		}
			
		Assert.assertEquals(4, count);
		LOGGER.info("search test successful");
		
	}
	
	@Test
	public void testDeleteHibernateDAO() throws SQLException{
		LOGGER.info("beginning delete test");
		
		//GIVEN
		 Identity identity = new Identity(10, "zick", "zick100", new Date());
		
		//WHEN
		dao.delete(identity);		
				
		//THEN
		Connection c = ds.getConnection();
		PreparedStatement selectAll = c.prepareStatement("SELECT * from IDENTITIES" );
		ResultSet results = selectAll.executeQuery();
			
		int count = 0;
		while(results.next()){
			count++;
		}
			
		Assert.assertEquals(5, count);
		LOGGER.info("delete test successful");
		
	}
	

	
	@After
	public void clearDatabase() throws SQLException{
		LOGGER.info("begin cleaning");
		Connection c = ds.getConnection();
		PreparedStatement delete = c.prepareStatement("DELETE from IDENTITIES" );
		delete.execute();
		LOGGER.info("finish cleaning");
	}
}
