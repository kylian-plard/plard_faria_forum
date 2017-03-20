package com.plard_faria_forum.modele;

import javax.servlet.http.HttpServletRequest;

public class FormChangeLevel {
	public static final String ATT_SESSION_USER	= "user";
	private static final String CHAMP_LEVEL = "level";
	private static final String CHAMP_ID = "idUser";

	private DAOUser daoUser;

	public FormChangeLevel(DAOUser daoU) {
		daoUser=daoU;
    }

	public boolean connect(HttpServletRequest request) throws DAOException{
		int level=Integer.parseInt(request.getParameter(CHAMP_LEVEL));
		String sujet=getValeurChamp(request, CHAMP_ID);
	    if(sujet==null) return false;
	    else {
	    	try {
	    		daoUser.changeLevel(level, sujet);
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