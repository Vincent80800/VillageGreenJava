package com.villagegreen.Modele;

import com.villagegreen.Util.DBConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProduitDAO{

    public static ObservableList<Produit> searchProduits() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM prod;";
        try {
            ResultSet rsProds = DBConnector.dbExecuteQuery(selectStmt);
            return getProduitsList(rsProds);
        } catch (SQLException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Produit> getProduitsList(ResultSet rs) throws SQLException {
        ObservableList<Produit> prodList = FXCollections.observableArrayList();
        while (rs.next()) {
            Produit prod = new Produit();

            prod.setReference_produit(rs.getString("pro_ref"));
            prod.setLibelle_produit(rs.getString("pro_lib"));
            prod.setDescription_produit(rs.getString("pro_des"));
            prod.setId_produit(rs.getInt("pro_id"));
            prod.setPrixV_produit(rs.getDouble("pro_priV"));
            prod.setPrixA_produit(rs.getDouble("pro_priA"));
            prod.setId_rubrique(rs.getInt("ru2_id"));

            prodList.add(prod);
        }
        return prodList;
    }

    public static Produit searchProduit(String LIBprod) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM prod WHERE pro_lib="+"'"+LIBprod+"'";
        try {
            ResultSet rsProd = DBConnector.dbExecuteQuery(selectStmt);
            return getProduitFromResultSet(rsProd);
        } catch (SQLException e) {
            System.out.println("Error while searching produit with LIBELLE" + LIBprod + e);
            throw e;
        }
    }

    private static Produit getProduitFromResultSet(ResultSet rs) throws SQLException {
        Produit prod = null;
        if (rs.next()) {
            prod = new Produit();
            prod.setReference_produit(rs.getString("pro_ref"));
            prod.setLibelle_produit(rs.getString("pro_lib"));
            prod.setDescription_produit(rs.getString("pro_des"));
            prod.setId_produit(rs.getInt("pro_id"));
            prod.setPrixV_produit(rs.getDouble("pro_priV"));
            prod.setPrixA_produit(rs.getDouble("pro_priA"));
            prod.setId_rubrique(rs.getInt("ru2_id"));
        }
        return prod;
    }

    public static void deleteProdWithId(int IDprod) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM prod\n" + "WHERE pro_id="+ IDprod + ";";

        try {
            DBConnector.dbExecuteUpdate(updateStmt);
        }catch (SQLException e) {
            System.out.println("Error while DELETE" + e);
            throw e;
        }
    }

    public static void updateNomProd(String refProd, String libProd, String desProd, String IdProd, String priVProd, String priAProd, String IdRub) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE prod\n" +
                "SET pro_ref = '" + refProd + "',\n" +
                "pro_lib = '" + libProd + "',\n" +
                "pro_des = '" + desProd + "',\n" +
                "pro_id = '" + IdProd + "',\n" +
                "pro_priV = '" + priVProd + "',\n" +
                "pro_priA = '" + priAProd + "',\n" +
                "ru2_id = '" + IdRub + "'\n" +
                "WHERE pro_id = '" + IdProd + "';";
        System.out.println(updateStmt);
        try {
            DBConnector.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error while UPDATE" + e);
            throw e;
        }
    }

    public static void insertProd(String refProd, String libProd, String desProd, String IdProd, String priVProd, String priAProd, String IdRub) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO prod\n" +
                "(pro_ref, pro_lib, pro_des, pro_id, pro_priV, pro_priA, ru2_id, fou_id, pro_aff)\n" +
                "VALUES\n" +
                "('"+refProd+"', '"+libProd+"', '"+desProd+"', '"+IdProd+"', '"+priVProd+"', '"+priAProd+"', '"+IdRub+"', 1, 1);";
        System.out.println(updateStmt);
        try {
            DBConnector.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error while INSERT! " + e);
            throw e;
        }
    }
}
