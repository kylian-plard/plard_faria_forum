package com.plard_faria_forum.modele;

public class Sujet {
	private int id;
	private String libelle;
	private String date;
	private String auteur;
	private int salon;

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getDate() {
		return date;
	}

	public String getAuteur() {
		return auteur;
	}

	public int getSalon() {
		return salon;
	}

	public void setId(int i) {
		id = i;
	}
	public void setLibelle(String l) {
		libelle = l;
	}
	public void setDate(String d) {
		date = d;
	}
	public void setAuteur(String a) {
		auteur = a;
	}
	public void setSalon(int s) {
		salon = s;
	}
}