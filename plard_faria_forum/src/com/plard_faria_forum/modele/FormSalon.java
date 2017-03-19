package com.plard_faria_forum.modele;

import javax.servlet.http.HttpServletRequest;

public final class FormSalon {
	public static final String ATT_SESSION_USER	= "user";
	private static final String CHAMP_ACTION = "action";
	private static final String CHAMP_LIBELLE = "titre";
	private static final String CHAMP_MSG = "msg";
	private static final String CHAMP_IDSALON = "idSalon";

	private DAOSalon daoSalon;

	public FormSalon(DAOSalon daoS) {
		daoSalon=daoS;
    }

	public boolean connect(HttpServletRequest request) throws DAOException{
		if(Integer.parseInt(request.getParameter(CHAMP_ACTION))==0) {
			String libelle=getValeurChamp(request, CHAMP_LIBELLE);
			String msg=getValeurChamp(request, CHAMP_MSG);
		    if(libelle==null || msg==null) return false;
		    else {
		    	try {
		    		daoSalon.creer(libelle, msg);
		    		return true;
		    	} catch(DAOException e) {
		    		e.printStackTrace();
		    		throw new DAOException("Échec de la réponse pour une raison inconnue !");
		    	}
		    }
		}
		else {
			try {
				daoSalon.sup(Integer.parseInt(request.getParameter(CHAMP_IDSALON)));
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