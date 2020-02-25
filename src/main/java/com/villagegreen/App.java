package com.villagegreen;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.villagegreen.Modele.Client;
import com.villagegreen.Modele.Client2;
import com.villagegreen.Modele.Produit;
import com.villagegreen.Util.DBConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        System.out.println(App.class.getResource("../../com.villagegreen/Accueil.fxml"));
        scene = new Scene(loadFXML("Accueil"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../../com.villagegreen/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        App.launch();
    }


}
