package fr.epita.iamweb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iamtesting.datamodel.Identity;
import fr.epita.iamtesting.services.HibernateDAO;

/**
 * Servlet implementation class SearchingIdentity
 */
public class SearchingIdentity extends AbstractSpringServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = LogManager.getLogger(SearchingIdentity.class);
	
	@Inject
	HibernateDAO dao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchingIdentity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				try {
					
					LOGGER.info("into search");
					final String displayName = request.getParameter("displayName");
					
					LOGGER.info("Searching for this name: " + displayName);
					request.getSession().setAttribute("previousSearch",displayName);
					
					Identity identity = new Identity(-1,displayName,null,null);
					
					List<Identity> result = new ArrayList<Identity>();

					LOGGER.info("before search");
					
					if(dao != null){
						if(request.getParameter("showAll") != null || request.getSession().getAttribute("previousSearch").equals("all")){
							result = dao.showAll();
							request.getSession().setAttribute("previousSearch","all");
							LOGGER.info("List all");
						}
						else{
							request.getSession().setAttribute("previousSearch",displayName);
							result = dao.search(identity);
						}
						LOGGER.info(result.size());
					}
					else{
						LOGGER.info("null");
					}
					LOGGER.info("Search identity successfully");
					
					
					
					request.getSession().setAttribute("message", "Found " + result.size() + " identity (identities)");
					request.getSession().setAttribute("identityFound", result);

					response.sendRedirect("search.jsp");

				} catch (final Exception pe) {
					pe.printStackTrace();
					LOGGER.info("error happen");
					request.getSession().setAttribute("message",
							"A problem occured with searching, contact the admistrator at ...@admin.com");
					response.sendRedirect("search.jsp");
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOGGER.info(request.getParameter("action"));
		if(request.getParameter("action").equals("modify")){
			try {	
				LOGGER.info("finding modify identity");
				final String uid = request.getParameter("selection");
	
				
				LOGGER.info("Modifying the identity with uid: " + uid);
				
				
				Identity result = null;
	
				LOGGER.info("before search");
				
				if(dao != null){
					result = dao.searchByID(Integer.parseInt(uid));
				}
				else{
					LOGGER.info("null");
				}
				LOGGER.info("Found the identity that needs to be modified");
				
				
				
				request.getSession().setAttribute("message", "Modifying the identity with uid: " + uid);
				request.getSession().setAttribute("identityModify", result);
	
				response.sendRedirect("modify.jsp");
				request.getSession().setAttribute("message", null);
				request.getSession().removeAttribute("identityFound");
	
			} catch (final Exception pe) {
				pe.printStackTrace();
				LOGGER.info("error happen");
				request.getSession().setAttribute("message",
						"A problem occured with the identity creation, contact the admistrator at ...@admin.com");
				response.sendRedirect("create.jsp");
			}
		}
		else{
			LOGGER.info("prepare to delete");
			final String uid = request.getParameter("selection");

			
			LOGGER.info("Deleting the identity with uid: " + uid);
			
			
			Identity result = new Identity(Integer.parseInt(uid),null,null,null);
			
			LOGGER.info("before delete");
			
			if(dao != null){
				dao.delete(result);
			}
			else{
				LOGGER.info("null");
			}
			
			LOGGER.info("Identity is deleted!");
			doGet(request,response);
		}
	}

	
}
