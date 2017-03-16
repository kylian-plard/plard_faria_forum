package com.plard_faria_forum.controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plard_faria_forum.modele.DAOFactory;
import com.plard_faria_forum.modele.DAOSalon;
import com.plard_faria_forum.modele.FormConnexion;
import com.plard_faria_forum.modele.Salon;

/**
 * Servlet implementation class Accueil
 */
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SESSION_CONNECT="user";
	public static final String ATT_SALONS="salons";
	public static final String INDEX="/hello";
	public static final String VUE="/WEB-INF/accueil.jsp";

	private DAOSalon daoSalon;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
    }

    public void init() throws ServletException {
		// Récupération d'une instance de notre DAO Utilisateur
		daoSalon=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getSalonDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session=request.getSession();
		
		// Si l'objet utilisateur n'existe pas dans la session en cours, alors l'utilisateur n'est pas connecté.
		if(session.getAttribute(ATT_SESSION_CONNECT)==null) response.sendRedirect(request.getContextPath()+INDEX);
		else {
			// Récupération et envoi à la vue de la liste des salons
			ArrayList<Salon> liste=daoSalon.trouver_all();
			request.setAttribute(ATT_SALONS, liste);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
