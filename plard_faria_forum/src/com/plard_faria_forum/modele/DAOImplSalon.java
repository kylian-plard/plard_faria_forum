package com.plard_faria_forum.modele;

import static com.plard_faria_forum.modele.DAOUtilitaire.fermeturesSilencieuses;
import static com.plard_faria_forum.modele.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOImplSalon implements DAOSalon {
	private static final String SQL_SELECT_ALL	= "SELECT * FROM Salon";
	private static final String SQL_INSERT		= "INSERT INTO Salon(id, libelle, description) VALUES (null, ?, ?)";
	private static final String SQL_DELETE		= "DELETE FROM Salon WHERE id = ?";

	private DAOFactory daoFactory;

    DAOImplSalon(DAOFactory dao) {
        daoFactory=dao;
    }

	// Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao
	public ArrayList<Salon> trouver_all() throws DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<Salon> liste=new ArrayList<Salon>();
		try {
			// Récupération d'une connexion depuis la Factory
			connexion=daoFactory.getConnection();
			preparedStatement=initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
			resultSet=preparedStatement.executeQuery();

			// Parcours de la ligne de données de l'éventuel ResulSet retourné
			while(resultSet.next()) {
				liste.add(map(resultSet));
			}
			return liste;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
	}

	// Implémentation de la méthode creer() définie dans l'interface UtilisateurDao
	public void creer(String l, String d) throws IllegalArgumentException, DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet valeursAutoGenerees=null;
		try {
			// Récupération d'une connexion depuis la Factory
			connexion=daoFactory.getConnection();
			preparedStatement=initialisationRequetePreparee(connexion, SQL_INSERT, true, l, d);
			int statut=preparedStatement.executeUpdate();

			// Analyse du statut retourné par la requête d'insertion
			if(statut==0) throw new DAOException("Échec de la création du salon, aucune ligne ajoutée dans la table.");
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

    // Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne issue de la table des utilisateurs (un ResultSet) et un bean Utilisateur.
    private static Salon map(ResultSet resultSet) throws SQLException {
        Salon s=new Salon();
        s.setId(resultSet.getInt("id"));
        s.setLibelle(resultSet.getString("libelle"));
        s.setDescription(resultSet.getString("description"));
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
			if(statut==0) throw new DAOException("Échec de la suppresion du salon, aucune ligne supprimée dans la table.");
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
    }
}