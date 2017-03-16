package com.plard_faria_forum.controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plard_faria_forum.modele.DAOFactory;
import com.plard_faria_forum.modele.DAOUser;
import com.plard_faria_forum.modele.FormConnexion;
import com.plard_faria_forum.modele.User;

/**
 * Servlet implementation class HelloWorld
 */

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_ERROR = "error";
	public static final String ATT_MESSAGE = "msg";
	public static final String ATT_DATE	= "date";
	public static final String ATT_SESSION_USER	= "user";
	public static final String ATT_SESSION_CONNECT = "isConnected";
	public static final String VUE = "/WEB-INF/index.jsp";
	public static final String ACCUEIL = "/accueil";

	private DAOUser daoUser;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
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
        /* Récupération de la date courante */
        LocalDateTime dt=LocalDateTime.now();

        /* Conversion de la date en String selon le format défini */
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String date=dt.format(formatter).toString();

        request.setAttribute(ATT_DATE, date);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de la session depuis la requête
        HttpSession session = request.getSession();

		FormConnexion form=new FormConnexion(daoUser);
		if(form.checkData(request)) { // Si les champs sont remplis
			User u=form.connect(request);
			if(u!=null) { // Si l'uilisateur existe
				// Si aucune erreur de validation n'a eu lieu, alors ajout du bean Utilisateur à la session, sinon suppression du bean de la session.
				session.setAttribute(ATT_SESSION_USER, u);
				session.setAttribute(ATT_SESSION_CONNECT, true);
				response.sendRedirect(request.getContextPath()+ACCUEIL);
			}
			else { // Si l'utilisateur n'existe pas
				session.setAttribute(ATT_SESSION_USER, null);
				session.setAttribute(ATT_SESSION_CONNECT, null);
				request.setAttribute(ATT_MESSAGE, "Mauvais identifiant ou mot de passe !");
				request.setAttribute(ATT_ERROR, true);
				doGet(request, response);
			}
		}
		else { // Si les champs ne sont pas remplis
			request.setAttribute(ATT_MESSAGE, "Vous devez renseigner tout les champs !");
			request.setAttribute(ATT_ERROR, true);
			doGet(request, response);
		}
	}
}