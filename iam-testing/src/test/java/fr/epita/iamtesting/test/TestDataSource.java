/**
 * 
 */
package fr.epita.iamtesting.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iamtesting.datamodel.Identity;
import fr.epita.iamtesting.exceptions.SaveDAOException;
import fr.epita.iamtesting.services.JDBCIdentityDAO;

/**
 * @author Zick
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestDataSource {

	private static final Logger LOGGER = LogManager.getLogger(TestDataSource.class);
	
	@Autowired
	DataSource ds;
	
	@Inject
	JDBCIdentityDAO dao;
	
	@Test
	public void testDatasource() throws SQLException{
		
		//Given the datasource in pom file
		
		//When
		Connection c = ds.getConnection();
		
		//Then
		LOGGER.info("The logger said: {}", c.getSchema());
	}
	
	@Test
	public void testJDBCDAO() throws SQLException, SaveDAOException, ClassNotFoundException{
		//Given
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		
		Identity i = new Identity(10,"zick","zick2",new Date());
		
		//When
		dao.save(i);
		
		//Then
		
	}
}
