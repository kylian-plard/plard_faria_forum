package com.plard_faria_forum.controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plard_faria_forum.modele.DAOException;
import com.plard_faria_forum.modele.DAOFactory;
import com.plard_faria_forum.modele.DAOSalon;
import com.plard_faria_forum.modele.DAOUser;
import com.plard_faria_forum.modele.Salon;
import com.plard_faria_forum.modele.User;

/**
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SESSION_CONNECT = "user";
	public static final String DECO = "/deconnexion";
	public static final String INDEX = "/hello";
	public static final String VUE = "/WEB-INF/deleteUser.jsp";

	private DAOUser daoUser;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
    }

    public void init() throws ServletException {
    	daoUser=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        /* Récupération de la session depuis la requête */
		HttpSession session=request.getSession();
		
		// Si l'objet utilisateur n'existe pas dans la session en cours, alors l'utilisateur n'est pas connecté.
		if(session.getAttribute(ATT_SESSION_CONNECT)==null) response.sendRedirect(request.getContextPath()+INDEX);
		else this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
		
        /* Récupération de la session depuis la requête */
		HttpSession session=request.getSession();
		
		// Si l'objet utilisateur n'existe pas dans la session en cours, alors l'utilisateur n'est pas connecté.
		User u=(User)session.getAttribute(ATT_SESSION_CONNECT);
		if(session.getAttribute(ATT_SESSION_CONNECT)==null) response.sendRedirect(request.getContextPath()+INDEX);
		else {
			try {
				daoUser.sup(u.getIdentifiant());
			} catch (DAOException e) {
				System.out.println("Erreur suppresion d'un utilisateur");
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+DECO);
		}
	}
}