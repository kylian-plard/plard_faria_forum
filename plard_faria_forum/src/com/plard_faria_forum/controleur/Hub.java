package com.plard_faria_forum.controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plard_faria_forum.modele.DAOException;
import com.plard_faria_forum.modele.DAOFactory;
import com.plard_faria_forum.modele.DAOMessage;
import com.plard_faria_forum.modele.DAOSujet;
import com.plard_faria_forum.modele.FormSujet;
import com.plard_faria_forum.modele.Sujet;

/**
 * Servlet implementation class Hub
 */
public class Hub extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SESSION_CONNECT = "user";
	public static final String ATT_SUJETS = "sujets";
	public static final String PARAM_IDSALON = "id";
	public static final String INDEX = "/hello";
	public static final String VUE = "/WEB-INF/salon.jsp";

	private DAOSujet daoSujet;
	private DAOMessage daoMessage;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hub() {
        super();
    }

    public void init() throws ServletException {
		daoSujet=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getSujetDao();
		daoMessage=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDao();
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
		else {
			// Récupération et envoi à la vue de la liste des salons
			request.setAttribute("libelle", request.getParameter(PARAM_IDSALON));
			ArrayList<Sujet> liste=daoSujet.trouver(Integer.parseInt(request.getParameter(PARAM_IDSALON)));
			request.setAttribute(ATT_SUJETS, liste);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		FormSujet form=new FormSujet(daoSujet, daoMessage);
		try {
			form.connect(request);
		} catch (DAOException e) {
			System.out.println("Erreur validation du formulaire d'ajout de sujet");
			e.printStackTrace();
		}
		doGet(request, response);
	}
}