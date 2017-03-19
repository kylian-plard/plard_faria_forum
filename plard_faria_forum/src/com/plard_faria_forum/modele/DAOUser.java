package com.plard_faria_forum.modele;

import java.util.ArrayList;

public interface DAOUser {
    void creer(String id, String mdp) throws DAOException;
    void changeLevel(int level, String id) throws DAOException;
    void sup(String id) throws DAOException;
    User trouver(String id, String mdp) throws DAOException;
    ArrayList<User> trouver_no_admin() throws DAOException;
}