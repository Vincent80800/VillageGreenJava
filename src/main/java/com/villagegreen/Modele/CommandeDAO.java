package com.villagegreen.Modele;

import com.villagegreen.Util.DBConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeDAO {

    public static ObservableList<Commande> searchCommandeByCli(int IDcli) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM comm JOIN clie ON comm.cli_id = clie.cli_id WHERE comm.cli_id=" +IDcli;
        try {
            ResultSet rsCom = DBConnector.dbExecuteQuery(selectStmt);
            ObservableList<Commande> comList = getCommandeFromResultSet(rsCom);
            return comList;
        } catch (SQLException e) {
            System.out.println("Error while searching commande client with ID" + IDcli + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getCommandeFromResultSet(ResultSet rs) throws SQLException {
        ObservableList<Commande> comList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande com = new Commande();
            com.setId_commande(rs.getInt("com_id"));
            com.setPrix_total(rs.getDouble("com_tot"));
            com.setDate_commande(rs.getDate("com_dat"));
            com.setStatut_commande(rs.getString("com_eta"));
            com.setPrix_total(rs.getDouble("com_tot"));
            com.setAdresse_facture(rs.getString("fac_adr"));
            com.setCodePostal_facture(rs.getString("fac_cp"));
            com.setVille_facture(rs.getString("fac_vil"));

            comList.add(com);
        }
        return comList;
    }

    public static ObservableList<Commande> searchCommandes() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM comm;";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getCommandesList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getCommandesList(ResultSet rs) throws SQLException {
        ObservableList<Commande> commList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande comm = new Commande();

            comm.setId_commande(rs.getInt("com_id"));
            comm.setDate_commande(rs.getDate("com_dat"));
            comm.setStatut_commande(rs.getString("com_eta"));
            comm.setAdresse_facture(rs.getString("fac_adr"));
            comm.setCodePostal_facture(rs.getString("fac_cp"));
            comm.setVille_facture(rs.getString("fac_vil"));
            comm.setPrix_total(rs.getDouble("com_tot"));

            commList.add(comm);
        }
        return commList;
    }

}
