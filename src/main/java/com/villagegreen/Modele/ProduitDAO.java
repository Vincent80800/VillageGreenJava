package com.villagegreen.Modele;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.villagegreen.Util.DBConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.hildan.fxgson.FxGson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProduitDAO{

    public static ObservableList<Produit> searchProduits() throws IOException, SQLException {
        ArrayList<Produit> resultat = new ArrayList<>();
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/getAllProd/";

            URL api = new URL(sURL);
            URLConnection request = api.openConnection();
            request.connect();

            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            JsonParser jp = null;
            JsonArray root = (JsonArray) jp.parseReader(new InputStreamReader((InputStream) request.getContent()));
            Produit[] liste = gg.fromJson(root, Produit[].class);

            resultat.addAll(Arrays.asList(liste));
        }
        catch (Exception er) {
            System.out.println("Un problème est survenue");
            System.out.println(er.getMessage());
            throw er;
        }
        return getProduitsList(resultat);
    }


    public static ObservableList<Produit> getProduitsList(ArrayList arrayList) throws SQLException, IOException {
        ObservableList<Produit> prodList = FXCollections.observableArrayList(arrayList);
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

    public static void deleteProdWithId(int id) throws IOException, SQLException {
        ArrayList<Produit> resultat = new ArrayList<>();
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/deleteProById/" + id;

            URL api = new URL(sURL);
            URLConnection request = api.openConnection();
            request.connect();

            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            JsonParser jp = null;
            JsonArray root = (JsonArray) jp.parseReader(new InputStreamReader((InputStream) request.getContent()));
            Produit[] liste = gg.fromJson(root, Produit[].class);

            resultat.addAll(Arrays.asList(liste));
        }
        catch (Exception er) {
            System.out.println("Un problème est survenue");
            System.out.println(er.getMessage());
            throw er;
        }
    }


    public static void updateProById(Produit produit) throws IOException, SQLException {
        ArrayList<Produit> resultat = new ArrayList<>();
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/updateProById/";

            URL api = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection) api.openConnection();
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/json; utf-8");
            request.setRequestProperty("Accept", "application/json");
            request.setDoOutput(true);


            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().setLenient().create();
            JsonParser jp = new JsonParser();
            String s = gg.toJson(produit);
            try (OutputStream os = request.getOutputStream()) {
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
        } catch (Exception er) {

            System.out.println("Un problème est survenue");
            System.out.println(er.getMessage());
        }
    }

    public static void insertPro(Produit produit) throws IOException, SQLException {
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/insertPro/";
            URL api = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection)api.openConnection();
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/json; utf-8");
            request.setRequestProperty("Accept", "application/json");
            request.setDoOutput(true);

            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().setLenient().create();
            JsonParser jp = new JsonParser();

            String s = gg.toJson(produit);
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
