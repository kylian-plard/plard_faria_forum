package com.plard_faria_forum.modele;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class FormMsg {
	public static final String ATT_SESSION_USER	= "user";
	private static final String CHAMP_ACTION = "action";
	private static final String CHAMP_MSG = "msg";
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_IDMESSAGE = "idMessage";

	private DAOMessage daoMessage;

	public FormMsg(DAOMessage daoM) {
		daoMessage=daoM;
    }

	public boolean connect(HttpServletRequest request) throws DAOException{
		if(Integer.parseInt(request.getParameter(CHAMP_ACTION))==0) {
			String msg=getValeurChamp(request, CHAMP_MSG);
			int sujet=Integer.parseInt(request.getParameter(CHAMP_ID));
		    if(msg==null) return false;
		    else {
		    	try {
		    		/* Récupération de la date courante */
		            LocalDateTime dt=LocalDateTime.now();
	
		            /* Conversion de la date en String selon le format défini */
		            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		            String date=dt.format(formatter).toString();
	
		            // Récupération de la session depuis la requête
		            HttpSession session=request.getSession();
	
		    		daoMessage.creer(msg, date, ((User)session.getAttribute(ATT_SESSION_USER)).getIdentifiant(), sujet);
		    		return true;
		    	} catch(DAOException e) {
		    		e.printStackTrace();
		    		throw new DAOException("Échec de la réponse pour une raison inconnue !");
		    	}
		    }
		}
		else {
			try {
				daoMessage.sup(Integer.parseInt(request.getParameter(CHAMP_IDMESSAGE)));
				return true;
			} catch(DAOException e) {
				e.printStackTrace();
	    		throw new DAOException("Échec de la réponse pour une raison inconnue !");
			}
		}
	}

	// Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur=request.getParameter(nomChamp);
		if(valeur==null || valeur.trim().length()==0) return null;
		else return valeur.trim();
	}
}