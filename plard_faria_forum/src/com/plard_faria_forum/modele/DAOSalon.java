package com.plard_faria_forum.modele;

import java.util.ArrayList;

public interface DAOSalon {
    void creer(String libelle, String description) throws DAOException;
    void sup(int id) throws DAOException;
    ArrayList<Salon> trouver_all() throws DAOException;
}