package com.plard_faria_forum.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plard_faria_forum.modele.DAOFactory;
import com.plard_faria_forum.modele.DAOException;
import com.plard_faria_forum.modele.DAOUser;
import com.plard_faria_forum.modele.FormCreateUser;

/**
 * Servlet implementation class Connexion
 */
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_ERROR = "error";
	public static final String ATT_MESSAGE = "msg";
	public static final String VUE = "/WEB-INF/createUser.jsp";
	public static final String INDEX = "/hello";

	private DAOUser daoUser;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
    }

	public void init() throws ServletException {
		// Récupération d'une instance de notre DAO Utilisateur
		daoUser=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormCreateUser form=new FormCreateUser(daoUser);
		try {
			if(form.connect(request)) response.sendRedirect(request.getContextPath()+INDEX);
			else {
				request.setAttribute(ATT_MESSAGE, "Vous devez renseigner tout les champs !");
				request.setAttribute(ATT_ERROR, true);
			}
		} catch (DAOException e) {
			request.setAttribute(ATT_MESSAGE, e.getMessage());
			request.setAttribute(ATT_ERROR, true);
		}
		doGet(request, response);
	}
}