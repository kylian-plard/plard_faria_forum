package com.plard_faria_forum.modele;

public class User {
	private int id;
	private String identifiant;

	public User() {}

	public int getId() {
		return id;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String p) {
		identifiant=p;
	}
}