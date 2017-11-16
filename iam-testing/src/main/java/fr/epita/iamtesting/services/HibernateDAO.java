package fr.epita.iamtesting.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.epita.iamtesting.datamodel.Identity;

public class HibernateDAO {
	
	@Inject
	private SessionFactory factory;
	
	/**
	 * This is a save or update method, it will record or update the identity in the database
	 * @param identity is the identity that will be recorded or updated
	 */
	public void saveOrUpdate(Identity i){
		Session s = factory.openSession();
		Transaction x = s.beginTransaction();
		
		s.saveOrUpdate(i);
		x.commit();
	}
	
	/**
	 * This is a showAll method, it will list all the identities in the database 
	 * @return List of result identities
	 */
	public List<Identity> showAll(){
		
		Session s = factory.openSession();
		
		return s.createQuery("from Identity", Identity.class).list();
	}
	
	/**
	 * This is a search method, it will search if the identity exist and return a list of result
	 * @param identity is the identity to search
	 * @return List of result identities
	 */
	public List<Identity> search(Identity i){
		Session s = factory.openSession();
		return s.createQuery("from Identity where NAME = '" + i.getDisplayName() + "'", Identity.class).list();
	}
	
	/**
	 * This is a search by id method, it will search for the identity with such id and return an Identity
	 * @param id is the identity's id
	 * @return an Identity
	 */
	public Identity searchByID(int id){
		Session s = factory.openSession();
		return s.get(Identity.class, id);
	}
	
	/**
	 * This is a delete method, it will delete an identity from database
	 * @param i the identity to delete
	 */
	public void delete(Identity i){
		Session s = factory.openSession();
		Transaction x = s.beginTransaction();
		
		s.delete(i);
		x.commit();
	}
	
}
