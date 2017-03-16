package com.plard_faria_forum.modele;

import javax.servlet.http.HttpServletRequest;

public final class FormConnexion {
	private static final String CHAMP_ID	="id";
	private static final String CHAMP_MDP	="mdp";

	private DAOUser daoUser;

	public FormConnexion(DAOUser daoU) {
		daoUser=daoU;
    }

	public User connect(HttpServletRequest request) {
		String id=getValeurChamp(request, CHAMP_ID);
		String mdp=getValeurChamp(request, CHAMP_MDP);
    	try {
    		return daoUser.trouver(id, mdp); // Renvoi l'utilisateur ou null s'il n'existe pas
    	} catch(DAOException e) {
    		e.printStackTrace();
    		throw new DAOException("Échec de l'inscription pour une raison inconnue !");
    	}
	}

	public boolean checkData(HttpServletRequest request) {
		String id=getValeurChamp(request, CHAMP_ID);
		String mdp=getValeurChamp(request, CHAMP_MDP);
	    if(id==null || mdp==null) return false;
	    else return true;
	}

	// Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur=request.getParameter(nomChamp);
		if(valeur==null || valeur.trim().length()==0) return null;
		else return valeur.trim();
	}
}