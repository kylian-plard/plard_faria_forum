package com.plard_faria_forum.modele;

import static com.plard_faria_forum.modele.DAOUtilitaire.fermeturesSilencieuses;
import static com.plard_faria_forum.modele.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImplUser implements DAOUser {
	private static final String SQL_SELECT_PAR_ID	= "SELECT id, level FROM User WHERE id = ?";
	private static final String SQL_INSERT			= "INSERT INTO User(id, mdp, level) VALUES (?, MD5(?), 2)";

	private DAOFactory daoFactory;

    DAOImplUser(DAOFactory dao) {
        daoFactory=dao;
    }

	// Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao
	public User trouver(String id) throws DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		User u=null;
		try {
			// Récupération d'une connexion depuis la Factory
			connexion=daoFactory.getConnection();
			preparedStatement=initialisationRequetePreparee(connexion, SQL_SELECT_PAR_ID, false, id);
			resultSet=preparedStatement.executeQuery();

			// Parcours de la ligne de données de l'éventuel ResulSet retourné
			if(resultSet.next()) u=map(resultSet);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return u;
	}

	// Implémentation de la méthode creer() définie dans l'interface UtilisateurDao
	public void creer(String i, String m) throws IllegalArgumentException, DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet valeursAutoGenerees=null;
		try {
			// Récupération d'une connexion depuis la Factory
			connexion=daoFactory.getConnection();
			preparedStatement=initialisationRequetePreparee(connexion, SQL_INSERT, true, i, m);
			int statut=preparedStatement.executeUpdate();

			// Analyse du statut retourné par la requête d'insertion
			if (statut==0) throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

    // Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne issue de la table des utilisateurs (un ResultSet) et un bean Utilisateur.
    private static User map(ResultSet resultSet) throws SQLException {
        User u=new User();
        u.setIdentifiant(resultSet.getString("id"));
        u.setLevel(resultSet.getInt("level"));
        return u;
    }
}