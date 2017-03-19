package com.plard_faria_forum.modele;

import static com.plard_faria_forum.modele.DAOUtilitaire.fermeturesSilencieuses;
import static com.plard_faria_forum.modele.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOImplSujet implements DAOSujet {
	private static final String SQL_SELECT_PAR_IDSALON = "SELECT * FROM Sujet WHERE salon = ?";
	private static final String SQL_INSERT = "INSERT INTO Sujet(id, libelle, date, auteur, salon) VALUES (null, ?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM Sujet WHERE id = ?";

	private DAOFactory daoFactory;

    DAOImplSujet(DAOFactory dao) {
        daoFactory=dao;
    }

	// Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao
	public ArrayList<Sujet> trouver(int id) throws DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<Sujet> liste=new ArrayList<Sujet>();
		try {
			// Récupération d'une connexion depuis la Factory
			connexion=daoFactory.getConnection();
			preparedStatement=initialisationRequetePreparee(connexion, SQL_SELECT_PAR_IDSALON, false, id);
			resultSet=preparedStatement.executeQuery();

			// Parcours de la ligne de données de l'éventuel ResulSet retourné
			while(resultSet.next()) liste.add(map(resultSet));
			return liste;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
	}

	// Implémentation de la méthode creer() définie dans l'interface UtilisateurDao
	public int creer(String l, String d, String a, int s) throws IllegalArgumentException, DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet valeursAutoGenerees=null;
		try {
			// Récupération d'une connexion depuis la Factory
			connexion=daoFactory.getConnection();
			preparedStatement=initialisationRequetePreparee(connexion, SQL_INSERT, true, l, d, a, s);
			int statut=preparedStatement.executeUpdate();

			// Analyse du statut retourné par la requête d'insertion
			if(statut==0) throw new DAOException("Échec de la création du sujet, aucune ligne ajoutée dans la table.");
			else {
				valeursAutoGenerees=preparedStatement.getGeneratedKeys();
				valeursAutoGenerees.next();
				return valeursAutoGenerees.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

    // Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne issue de la table des utilisateurs (un ResultSet) et un bean Utilisateur.
    private static Sujet map(ResultSet resultSet) throws SQLException {
        Sujet s=new Sujet();
        s.setId(resultSet.getInt("id"));
        s.setLibelle(resultSet.getString("libelle"));
        s.setDate(resultSet.getString("date"));
        s.setAuteur(resultSet.getString("auteur"));
        s.setSalon(resultSet.getInt("salon"));
        return s;
    }

    public void sup(int id) throws DAOException{
    	Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet valeursAutoGenerees=null;
		try {
			// Récupération d'une connexion depuis la Factory
			connexion=daoFactory.getConnection();
			preparedStatement=initialisationRequetePreparee(connexion, SQL_DELETE, true, id);
			int statut=preparedStatement.executeUpdate();

			// Analyse du statut retourné par la requête d'insertion
			if(statut==0) throw new DAOException("Échec de la suppresion du sujet, aucune ligne supprimée dans la table.");
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
    }
}