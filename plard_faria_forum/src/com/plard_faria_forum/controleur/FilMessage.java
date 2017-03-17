package com.plard_faria_forum.controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plard_faria_forum.modele.DAOException;
import com.plard_faria_forum.modele.DAOFactory;
import com.plard_faria_forum.modele.DAOMessage;
import com.plard_faria_forum.modele.FormCreateUser;
import com.plard_faria_forum.modele.FormMsg;
import com.plard_faria_forum.modele.Message;
import com.plard_faria_forum.modele.Sujet;

/**
 * Servlet implementation class FilMessage
 */
public class FilMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SESSION_CONNECT = "user";
	public static final String ATT_MESSAGES = "messages";
	public static final String ATT_ERROR = "error";
	public static final String ATT_MESSAGE = "msg";
	public static final String PARAM_IDSUJET = "id";
	public static final String INDEX = "/hello";
	public static final String VUE = "/WEB-INF/sujet.jsp";

	private DAOMessage daoMessage;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilMessage() {
        super();
    }

    public void init() throws ServletException {
		// Récupération d'une instance de notre DAO Utilisateur
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
			ArrayList<Message> liste=daoMessage.trouver(Integer.parseInt(request.getParameter(PARAM_IDSUJET)));
			request.setAttribute(ATT_MESSAGES, liste);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

		FormMsg form=new FormMsg(daoMessage);
		try {
			if(!form.connect(request)) {
				request.setAttribute(ATT_MESSAGE, "Vous devez mettre un message pour répondre !");
				request.setAttribute(ATT_ERROR, true);
			}
		} catch (DAOException e) {
			request.setAttribute(ATT_MESSAGE, e.getMessage());
			request.setAttribute(ATT_ERROR, true);
		}
		doGet(request, response);
	}
}