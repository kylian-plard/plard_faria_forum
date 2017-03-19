package com.plard_faria_forum.modele;

import java.util.ArrayList;

public interface DAOMessage {
    void creer(String msg, String date, String auteur, int sujet) throws DAOException;
    void sup(int id) throws DAOException;
    ArrayList<Message> trouver(int i) throws DAOException;
}