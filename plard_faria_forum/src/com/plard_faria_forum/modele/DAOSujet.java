package com.plard_faria_forum.modele;

import java.util.ArrayList;

public interface DAOSujet {
    int creer(String libelle, String date, String auteur, int salon) throws DAOException;
    void sup(int id) throws DAOException;
    ArrayList<Sujet> trouver(int i) throws DAOException;
}