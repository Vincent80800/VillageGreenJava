package com.villagegreen.Modele;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.villagegreen.Util.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hildan.fxgson.FxGson;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientDAO{
    public static ObservableList<Client> getAllClients() throws IOException, SQLException {
        ArrayList<Client> resultat = new ArrayList<>();
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/getAllClie/";

            URL api = new URL(sURL);
            URLConnection request = api.openConnection();
            request.connect();

            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            JsonParser jp = new JsonParser();
            JsonArray root = (JsonArray) jp.parse(new InputStreamReader((InputStream) request.getContent()));
            Client[] liste = gg.fromJson(root, Client[].class);

            resultat.addAll(Arrays.asList(liste));
        }
        catch (Exception er) {
            System.out.println("Un problème est survenue");
            System.out.println(er.getMessage());
            throw er;
        }
        return getClientsList(resultat);
    }

    public static ObservableList<Client> searchClients() throws SQLException, ClassNotFoundException, IOException {
        String selectStmt = "SELECT * FROM clie;";
        try {
            ResultSet rsClis = DBConnector.dbExecuteQuery(selectStmt);
            return getClientsList(rsClis);
        } catch (SQLException | IOException e) {
            System.out.println("SQL select operation failed!  " + e);
            throw e;
        }
    }

    public static ObservableList<Client> getClientsList(ArrayList arrayList) throws SQLException, IOException {
        ObservableList<Client> cliList = FXCollections.observableArrayList(arrayList);
        return cliList;
    }

    public static void deleteCliWithId(int IDcli) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM clie\n" + "WHERE cli_id="+ IDcli + ";";

        try {
            DBConnector.dbExecuteUpdate(updateStmt);
        }catch (SQLException e) {
            System.out.println("Error while DELETE" + e);
            throw e;
        }
    }

    public static void updateNomCli(String nomCli, String preCli, String adrCli, String mailCli, String telCli, String IdCli, String IdVen) throws SQLException, ClassNotFoundException {

        String updateStmt = "UPDATE clie\n" +
                "SET cli_nom = '" + nomCli + "',\n" +
                "cli_pre = '" + preCli + "',\n" +
                "cli_adr = '" + adrCli + "',\n" +
                "cli_email = '" + mailCli + "',\n" +
                "cli_tel = '" + telCli + "',\n" +
                "cli_id = '" + IdCli + "',\n" +
                "ven_id = '" + IdVen + "'\n" +
                "WHERE cli_id = '" + IdCli + "';";
        System.out.println(updateStmt);
        try {
            DBConnector.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error while UPDATE" + e);
            throw e;
        }
    }

    public static void insertCli(String nomCli, String preCli, String adrCli, String mailCli, String telCli, String IdCli, String IdVen) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO clie\n" +
                "(cli_nom, cli_pre, cli_adr, cli_email, cli_tel, cli_id, ven_id, sta_id)\n" +
                "VALUES\n" +
                "('"+nomCli+"', '"+preCli+"', '"+adrCli+"', '"+mailCli+"', '"+telCli+"', '"+IdCli+"', '"+IdVen+"', 1);";
        System.out.println(updateStmt);
        try {
            DBConnector.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error while INSERT! " + e);
            throw e;
        }
    }

}
