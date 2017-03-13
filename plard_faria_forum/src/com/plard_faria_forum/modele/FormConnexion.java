package com.plard_faria_forum.modele;

import javax.servlet.http.HttpServletRequest;

public final class FormConnexion {
	private static final String CHAMP_ID	="id";
	private static final String CHAMP_MDP	="mdp";

	public User connect(HttpServletRequest request) {
		String id=getValeurChamp(request, CHAMP_ID);
		String mdp=getValeurChamp(request, CHAMP_MDP);
    	if(1==1) { // Tester connexion BDD pour récupérer user
	    	User u=new User();
	    	u.setIdentifiant(id);
	    	return u;
    	}
    	else return null;
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