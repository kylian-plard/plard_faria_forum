package com.plard_faria_forum.modele;

public class User {
	private String identifiant;
	private int level;

	public User() {}

	public String getIdentifiant() {
		return identifiant;
	}

	public int getLevel() {
		return level;
	}

	public void setIdentifiant(String p) {
		identifiant=p;
	}

	public void setLevel(int i) {
		level=i;
	}
}