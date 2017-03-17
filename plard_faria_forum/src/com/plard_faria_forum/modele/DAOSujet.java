package com.plard_faria_forum.modele;

import java.util.ArrayList;

public interface DAOSujet {
    void creer(String libelle, String date, String auteur, int salon) throws DAOException;
    ArrayList<Sujet> trouver(int i) throws DAOException;
}