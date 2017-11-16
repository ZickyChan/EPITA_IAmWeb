package fr.epita.iamweb;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class CreateIdentity
 */
public class CreateIdentity extends AbstractSpringServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(CreateIdentity.class);
	
	@Inject
	HibernateDAO dao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateIdentity() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			LOGGER.info("into create");
			final String displayName = request.getParameter("displayName");
			final String email = request.getParameter("email");
			final String rawDate = request.getParameter("date");

			
			LOGGER.info(displayName);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(rawDate);

			final Identity identity = new Identity(displayName, email, date);

			LOGGER.info("before create");
			
			if(dao != null){
				dao.saveOrUpdate(identity);
			}
			else{
				LOGGER.info("null");
			}
			LOGGER.info("Created identity successfully");

			request.getSession().setAttribute("message", "Identity has been successfully created");

			response.sendRedirect("create.jsp");

		} catch (final Exception pe) {
			pe.printStackTrace();
			LOGGER.info("error happen");
			request.getSession().setAttribute("message",
					"A problem occured with the identity creation, contact the admistrator at ...@admin.com");
			response.sendRedirect("create.jsp");
		}
	}

}
