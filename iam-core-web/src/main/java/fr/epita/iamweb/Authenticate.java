package fr.epita.iamweb;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class Authenticate
 */
public class Authenticate extends AbstractSpringServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = LogManager.getLogger(Authenticate.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String login = request.getParameter("firstName");
		final String password = request.getParameter("password");
		LOGGER.info("trying to authenticate with that login {}", login);
		
		final HttpSession session = request.getSession();
		
		if(login.equalsIgnoreCase("admin") && password.equalsIgnoreCase("123")){

			session.setAttribute("authenticated", true);
			response.sendRedirect("mainPage.jsp");
			
			LOGGER.info("authenticated");

		}
		else{
			session.setAttribute("authenticated", false);
			response.sendRedirect("index.jsp");
			LOGGER.info("not authenticated");
			
		}
	}

}
