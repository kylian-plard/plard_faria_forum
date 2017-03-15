package com.plard_faria_forum.modele;

import javax.servlet.http.HttpServletRequest;

public final class FormCreateUser {
	private static final String CHAMP_ID	= "id";
	private static final String CHAMP_MDP	= "mdp";

	private DAOUser daoUser;

	public FormCreateUser(DAOUser daoU) {
		daoUser=daoU;
    }

	public boolean connect(HttpServletRequest request) throws DAOException{
		String id=getValeurChamp(request, CHAMP_ID);
		String mdp=getValeurChamp(request, CHAMP_MDP);
	    if(id==null || mdp==null) return false;
	    else {
	    	try {
	    		daoUser.creer(id, mdp);
	    		return true;
	    	} catch(DAOException e) {
	    		e.printStackTrace();
	    		throw new DAOException("Échec de l'inscription pour une raison inconnue !");
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