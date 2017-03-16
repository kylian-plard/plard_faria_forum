package com.plard_faria_forum.modele;

public class Salon {
	private int id;
	private String libelle;
	private String description;

	public Salon() {}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int i) {
		id=i;
	}

	public void setLibelle(String l) {
		libelle=l;
	}

	public void setDescription(String d) {
		description=d;
	}
}