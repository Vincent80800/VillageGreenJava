package com.villagegreen.Modele;

import com.villagegreen.Util.DBConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DashBoardDAO {

    public static ObservableList<Commande> top10_clientca() throws SQLException {
        String selectStmt = "SELECT * FROM `top10_clientca`;";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getProduitsList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getProduitsList(ResultSet rs) throws SQLException {
        ObservableList<Commande> caList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande cli = new Commande();
            cli.setId_client(rs.getInt("Identifiant Client"));
            cli.setNom_client(rs.getString("Nom du Client"));
            cli.setCa(rs.getDouble("Chiffre d'Affaires"));
            caList.add(cli);
        }
        return caList;
    }

    public static ObservableList<Commande> top10_clientcomm() throws SQLException {
        String selectStmt = "SELECT * FROM `top10_clientcomm`;";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getCliCommList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getCliCommList(ResultSet rs) throws SQLException {
        ObservableList<Commande> caList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande cli = new Commande();
            cli.setId_client(rs.getInt("Identifiant Client"));
            cli.setNom_client(rs.getString("Nom du Client"));
            cli.setNbrComm(rs.getInt("Nombre de commande"));
            caList.add(cli);
        }
        return caList;
    }

    public static ObservableList<Commande> ca_fournisseur() throws SQLException {
        String selectStmt = "SELECT * FROM `ca_fournisseur`;";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getCAFouList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getCAFouList(ResultSet rs) throws SQLException {
        ObservableList<Commande> caList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande four = new Commande();
            four.setNom_fou(rs.getString("FOURNISSEUR"));
            four.setCa(rs.getDouble("CA"));
            caList.add(four);
        }
        return caList;
    }

    public static ObservableList<Commande> prod_four() throws SQLException {
        String selectStmt = "SELECT * FROM `prod_four`;";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getProdFourList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getProdFourList(ResultSet rs) throws SQLException {
        ObservableList<Commande> caList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande prod = new Commande();
            prod.setId_produit(rs.getInt("Identifiant Client"));
            prod.setLibelle_produit(rs.getString("Nom du Produit"));
            prod.setId_fou(rs.getInt("Identifiant Fournisseur"));
            prod.setNom_fou(rs.getString("Nom du Fournisseur"));
            caList.add(prod);
        }
        return caList;
    }

    public static ObservableList<Commande> prod_categ() throws SQLException {
        String selectStmt = "SELECT * FROM `prod_categ`;";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getProdCatList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getProdCatList(ResultSet rs) throws SQLException {
        ObservableList<Commande> caList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande cat = new Commande();
            cat.setId_produit(rs.getInt("Identifiant Produit"));
            cat.setLibelle_produit(rs.getString("Nom du Produit"));
            cat.setNomCat(rs.getString("Nom de la Catégorie"));
            cat.setNomSousCat(rs.getString("Nom de la Sous-Catégorie"));
            caList.add(cat);
        }
        return caList;
    }

    public static ObservableList<Commande> ca_typeclient() throws SQLException {
        String selectStmt = "SELECT * FROM `ca_typeclient`;";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getTypeCliList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getTypeCliList(ResultSet rs) throws SQLException {
        ObservableList<Commande> caList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande cat = new Commande();
            cat.setCatClient(rs.getString("CATEGORIE"));
            cat.setCa(rs.getDouble("CA"));
            caList.add(cat);
        }
        return caList;
    }

    public static ObservableList<Commande> CA_Mensuel(String annee) throws SQLException {
        String selectStmt = "CALL `CA_Mensuel`(" + annee +");";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getCaMenList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getCaMenList(ResultSet rs) throws SQLException {
        ObservableList<Commande> caList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande men = new Commande();
            men.setMois(rs.getInt("Mois"));
            men.setCa(rs.getDouble("Chiffre d'affaires mensuel"));
            caList.add(men);
        }
        return caList;
    }

    public static ObservableList<Commande> Top10_ProdCommande(String annee) throws SQLException {
        String selectStmt = "CALL `Top10_ProdCommande`(" + annee +");";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getTopCommList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getTopCommList(ResultSet rs) throws SQLException {
        ObservableList<Commande> caList = FXCollections.observableArrayList();
        while (rs.next()) {
            Commande comm = new Commande();
            comm.setReference_produit(rs.getString("pro_id"));
            comm.setLibelle_produit(rs.getString("pro_lib"));
            comm.setQte(rs.getInt("Quantité commandée"));
            comm.setNom_fou(rs.getString("fou_nom"));
            caList.add(comm);
        }
        return caList;
    }

    public static ObservableList<Commande> Top10_ProdRemunerateur(String annee) throws SQLException {
        String selectStmt = "CALL `Top10_ProdRemunerateur`(" + annee +");";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getTopRemList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Commande> getTopRemList(ResultSet rs) throws SQLException {
        ObservableList<Commande> caList = FXCollections.observableArrayList();

        while (rs.next()) {
            Commande comm = new Commande();
            comm.setReference_produit(rs.getString("pro_id"));
            comm.setLibelle_produit(rs.getString("pro_lib"));
            comm.setMarge(rs.getDouble("Marge"));
            comm.setNom_fou(rs.getString("fou_nom"));
            caList.add(comm);
        }
        return caList;
    }

}

