package com.villagegreen.Modele;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.villagegreen.Util.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hildan.fxgson.FxGson;


import java.io.*;
import java.net.HttpURLConnection;
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
            JsonParser jp = null;
            JsonArray root = (JsonArray) jp.parseReader(new InputStreamReader((InputStream) request.getContent()));
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

    public static ObservableList<Client> getClientsList(ArrayList arrayList) throws SQLException, IOException {
        ObservableList<Client> cliList = FXCollections.observableArrayList(arrayList);
        return cliList;
    }

    public static void deleteCliById(int id) throws IOException, SQLException {
        ArrayList<Client> resultat = new ArrayList<>();
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/deleteCliById/" + id;

            URL api = new URL(sURL);
            URLConnection request = api.openConnection();
            request.connect();

            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            JsonParser jp = null;
            JsonArray root = (JsonArray) jp.parseReader(new InputStreamReader((InputStream) request.getContent()));
            Client[] liste = gg.fromJson(root, Client[].class);

            resultat.addAll(Arrays.asList(liste));
        }
        catch (Exception er) {
            System.out.println("Un problème est survenue");
            System.out.println(er.getMessage());
            throw er;
        }

    }


/*    public static void deleteCliWithId(int IDcli) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM clie\n" + "WHERE cli_id="+ IDcli + ";";

        try {
            DBConnector.dbExecuteUpdate(updateStmt);
        }catch (SQLException e) {
            System.out.println("Error while DELETE" + e);
            throw e;
        }
    }*/

    public static void updateCliById(int id) throws IOException, SQLException {
        ArrayList<Client> resultat = new ArrayList<>();
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/updateCliById/";

            URL api = new URL(sURL);
            URLConnection request = api.openConnection();
            request.connect();

            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            JsonParser jp = null;
            JsonArray root = (JsonArray) jp.parseReader(new InputStreamReader((InputStream) request.getContent()));
            Client[] liste = gg.fromJson(root, Client[].class);

            resultat.addAll(Arrays.asList(liste));
        }
        catch (Exception er) {
            System.out.println("Un problème est survenue");
            System.out.println(er.getMessage());
            throw er;
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


    public static void insertCli(Client client) throws IOException, SQLException {
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/insertCli/";
            URL api = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection)api.openConnection();
            request.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/json; utf-8");
            request.setRequestProperty("Accept", "application/json");
            request.setDoOutput(true);


            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            JsonParser jp = new JsonParser();
            /*JsonArray root = (JsonArray) jp.parseReader(new InputStreamReader((InputStream) request.getContent()));*/

            String s = gg.toJson(client);
            try(OutputStream os = request.getOutputStream()) {
                byte[] input = s.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Reception des données
            String response = "";
            try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response += responseLine.trim();
                }
            }

            JsonObject reponse = jp.parse(response).getAsJsonObject();

            System.out.println(reponse.get("message").getAsString());
        }
        catch (Exception er) {
            System.out.println("Un problème est survenue");
            System.out.println(er.getMessage());
            throw er;
        }

    }


  /*  public static void insertCli(String nomCli, String preCli, String adrCli, String mailCli, String telCli, String IdCli, String IdVen) throws SQLException, ClassNotFoundException {
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
    }*/
}
