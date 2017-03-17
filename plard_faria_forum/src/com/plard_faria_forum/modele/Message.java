package com.plard_faria_forum.modele;

public class Message {
	private int id;
	private String msg;
	private String date;
	private String auteur;
	private int sujet;
	private int reponse;

	public int getId() {
		return id;
	}

	public String getMsg() {
		return msg;
	}

	public String getDate() {
		return date;
	}

	public String getAuteur() {
		return auteur;
	}

	public int getSujet() {
		return sujet;
	}

	public int getReponse() {
		return reponse;
	}

	public void setId(int i) {
		id = i;
	}

	public void setMsg(String m) {
		msg = m;
	}

	public void setDate(String d) {
		date = d;
	}

	public void setAuteur(String a) {
		auteur = a;
	}

	public void setSujet(int s) {
		sujet = s;
	}

	public void setReponse(int r) {
		reponse = r;
	}
}