package com.plard_faria_forum.modele;

public class User {
	private int id;
	private String prenom;
	private String nom;

	User() {}

	public int getId() {
		return id;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setPrenom(String p) {
		prenom=p;
	}

	public void setNom(String n) {
		nom=n;
	}
}