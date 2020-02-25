package com.villagegreen.Util;
import javafx.fxml.FXML;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBConnector {

    private static Connection conn = null;

    private static final String connURL = "jdbc:mysql://localhost:3306/village_green?serverTimezone=UTC";
    private static final String connUser = "root";
    private static final String connPass = "";

    public static void dbConnect() throws SQLException {

        try {

            conn = DriverManager.getConnection(connURL, connUser, connPass);
            System.out.println("ça marche ");
        } catch (Exception e) {
            System.out.println("la connexion a échoué" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();

        try {
            dbConnect();

            System.out.println("Select statement: " + queryStmt + "\n");

            stmt = conn.createStatement();

            resultSet = stmt.executeQuery(queryStmt);

            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem at executeQuery : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();

            stmt = conn.createStatement();

            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem at executeUpdate : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
}
