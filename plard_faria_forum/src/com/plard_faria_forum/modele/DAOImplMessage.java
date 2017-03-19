package com.plard_faria_forum.modele;

import static com.plard_faria_forum.modele.DAOUtilitaire.fermeturesSilencieuses;
import static com.plard_faria_forum.modele.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOImplMessage implements DAOMessage {
	private static final String SQL_SELECT_PAR_IDSUJET = "SELECT * FROM Message WHERE sujet = ? ORDER BY date";
	private static final String SQL_INSERT = "INSERT INTO Message(id, msg, date, auteur, sujet) VALUES (null, ?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM Message WHERE id = ?";

	private DAOFactory daoFactory;

    DAOImplMessage(DAOFactory dao) {
        daoFactory=dao;
    }

	// Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao
	public ArrayList<Message> trouver(int id) throws DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<Message> liste=new ArrayList<Message>();;
		try {
			// Récupération d'une connexion depuis la Factory
			connexion=daoFactory.getConnection();
			preparedStatement=initialisationRequetePreparee(connexion, SQL_SELECT_PAR_IDSUJET, false, id);
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
	public void creer(String m, String d, String a, int s) throws IllegalArgumentException, DAOException {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet valeursAutoGenerees=null;
		try {
			// Récupération d'une connexion depuis la Factory
			connexion=daoFactory.getConnection();
			preparedStatement=initialisationRequetePreparee(connexion, SQL_INSERT, true, m, d, a, s);
			int statut=preparedStatement.executeUpdate();

			// Analyse du statut retourné par la requête d'insertion
			if(statut==0) throw new DAOException("Échec de la création du message , aucune ligne ajoutée dans la table.");
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

    // Simple méthode utilitaire permettant de faire la correspondance (le mapping) entre une ligne issue de la table des utilisateurs (un ResultSet) et un bean Utilisateur.
    private static Message map(ResultSet resultSet) throws SQLException {
        Message m=new Message();
        m.setId(resultSet.getInt("id"));
        m.setMsg(resultSet.getString("msg"));
        m.setDate(resultSet.getString("date"));
        m.setAuteur(resultSet.getString("auteur"));
        m.setSujet(resultSet.getInt("sujet"));
        return m;
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
			if(statut==0) throw new DAOException("Échec de la suppresion du message, aucune ligne supprimée dans la table.");
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
    }
}