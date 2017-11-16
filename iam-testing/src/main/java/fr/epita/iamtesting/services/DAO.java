package fr.epita.iamtesting.services;

import java.util.List;

import fr.epita.iamtesting.datamodel.Identity;
import fr.epita.iamtesting.exceptions.*;


/**
 * This interface is the contract for other DAO such as FileIdentityDAO, JDBCIdentityDAO.
 * @author Zick
 *
 */
public interface DAO {
	public void save(Identity i) throws SaveDAOException;
	public void delete(Identity i) throws DeleteDAOException;
	public List<Identity> search(String name) throws SearchDAOException;
	public void update(Identity i) throws UpdateDAOException;
}
