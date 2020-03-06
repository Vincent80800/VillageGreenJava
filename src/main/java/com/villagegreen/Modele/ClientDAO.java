package com.villagegreen.Modele;

import com.google.gson.*;
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

    public static void updateCliById(Client client) throws IOException, SQLException {
        ArrayList<Client> resultat = new ArrayList<>();
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/updateCliById/";

            URL api = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection)api.openConnection();
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/json; utf-8");
            request.setRequestProperty("Accept", "application/json");
            request.setDoOutput(true);


            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().setLenient().create();
            JsonParser jp = new JsonParser();
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
        }

    }

    public static void insertCli(Client client) throws IOException, SQLException {
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/insertCli/";
            URL api = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection)api.openConnection();
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/json; utf-8");
            request.setRequestProperty("Accept", "application/json");
            request.setDoOutput(true);

            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().setLenient().create();
            JsonParser jp = new JsonParser();

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
        }

    }
}
