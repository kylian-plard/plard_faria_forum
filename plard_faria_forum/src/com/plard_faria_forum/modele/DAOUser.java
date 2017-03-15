package com.plard_faria_forum.modele;

public interface DAOUser {
    void creer(String id, String mdp) throws DAOException;
    User trouver(String email) throws DAOException;
}