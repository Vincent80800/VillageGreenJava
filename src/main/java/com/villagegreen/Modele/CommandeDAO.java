package com.villagegreen.Modele;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.villagegreen.Util.DBConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.hildan.fxgson.FxGson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandeDAO {

    public static ObservableList<Commande> searchCommandeByCli(int id) throws IOException, SQLException {
        ArrayList<Commande> resultat = new ArrayList<>();
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/getCommById/" + id;

            URL api = new URL(sURL);
            URLConnection request = api.openConnection();
            request.connect();

            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            JsonParser jp = null;
            JsonArray root = (JsonArray) jp.parseReader(new InputStreamReader((InputStream) request.getContent()));
            Commande[] liste = gg.fromJson(root, Commande[].class);

            resultat.addAll(Arrays.asList(liste));
        }
        catch (Exception er) {
            System.out.println("Un problème est survenue");
            System.out.println(er.getMessage());
            throw er;
        }
        return getCommandeFromResultSet(resultat);
    }

    public static ObservableList<Commande> getCommandeFromResultSet(ArrayList arrayList) throws SQLException {
        ObservableList<Commande> comList = FXCollections.observableArrayList(arrayList);
        return comList;
    }

    public static ObservableList<Commande> getAllCommande() throws IOException, SQLException {
        ArrayList<Commande> resultat = new ArrayList<>();
        try {
            String sURL = "https://dev.amorce.org/vboulard/VillageGreen/index.php/Api/getAllComm/";

            URL api = new URL(sURL);
            URLConnection request = api.openConnection();
            request.connect();

            Gson gg = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            JsonParser jp = null;
            JsonArray root = (JsonArray) jp.parseReader(new InputStreamReader((InputStream) request.getContent()));
            Commande[] liste = gg.fromJson(root, Commande[].class);

            resultat.addAll(Arrays.asList(liste));
        }
        catch (Exception er) {
            System.out.println("Un problème est survenue");
            System.out.println(er.getMessage());
            throw er;
        }
        return getCommandesList(resultat);
    }

    public static ObservableList<Commande> getCommandesList(ArrayList arrayList) throws SQLException, IOException {
        ObservableList<Commande> commList = FXCollections.observableArrayList(arrayList);
        return commList;
    }
}
