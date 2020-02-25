package com.villagegreen.Controller;

import com.villagegreen.App;
import com.villagegreen.Modele.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;

public class DashBoardController {

    @FXML
    private Label dashBoardLabel = new Label();

    @FXML
    private TableView<Commande> viewTable;

    @FXML
    private TableColumn<Commande, String> c1, c2, c4, c3;

    @FXML
    private Hyperlink link1, link2, link3, link4, link5, link6, link7, link8, link9;

    private String param1 = "link1",
                   param2 = "link2",
                   param3 = "link3",
                   param4 = "link4",
                   param5 = "link5",
                   param6 = "link6",
                   param7 = "link7",
                   param8 = "link8",
                   param9 = "link9",
                   year;
    Popup popup = new Popup();
    @FXML
    private void initialize() throws SQLException {

        this.link4.setOnAction((e) -> {
            try {
                dashBoardLabel.setText("Top 10 des clients qui génèrent le plus de chiffre d'affaires");
                getData(link4);
                c1.setText("Identifiant Client");
                c1.setCellValueFactory(cellData -> cellData.getValue().id_clientProperty().asString());
                c2.setText("Nom du Client");
                c2.setCellValueFactory(cellData -> cellData.getValue().nom_clientProperty());
                c3.setVisible(true);
                c3.setText("Chiffre d'Affaires");
                c3.setCellValueFactory(cellData -> cellData.getValue().caProperty().asString());
                c4.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        this.link5.setOnAction((e) -> {
            try {
                dashBoardLabel.setText("Top 10 des clients qui ont commandé le plus");
                getData(link5);
                c1.setText("Identifiant Client");
                c1.setCellValueFactory(cellData -> cellData.getValue().id_clientProperty().asString());
                c2.setText("Nom du Client");
                c2.setCellValueFactory(cellData -> cellData.getValue().nom_clientProperty());
                c3.setVisible(true);
                c3.setText("Nombre de Commande");
                c3.setCellValueFactory(cellData -> cellData.getValue().NbrCommProperty().asString());
                c3.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        this.link6.setOnAction((e) -> {
            try {
                dashBoardLabel.setText("Chiffre d'affaire par type de client");
                getData(link6);
                c1.setText("Catégories");
                c1.setCellValueFactory(cellData -> cellData.getValue().catClientProperty());
                c2.setText("Chiffre d'affaires");
                c2.setCellValueFactory(cellData -> cellData.getValue().caProperty().asString());
                c3.setVisible(false);
                c4.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        this.link7.setOnAction((e) -> {
            try {
                dashBoardLabel.setText("Chiffre d'affaire par fournisseur");
                getData(link7);
                c1.setText("Fournisseur");
                c1.setCellValueFactory(cellData -> cellData.getValue().Nom_fouProperty());
                c2.setText("Chiffre d'affaires");
                c2.setCellValueFactory(cellData -> cellData.getValue().caProperty().asString());
                c3.setVisible(false);
                c4.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        this.link8.setOnAction((e) -> {
            try {
                dashBoardLabel.setText("Produits classés par catégories");
                getData(link8);
                c1.setText("Identifiant Produit");
                c1.setCellValueFactory(cellData -> cellData.getValue().id_produitProperty().asString());
                c2.setText("Nom du Produit");
                c2.setCellValueFactory(cellData -> cellData.getValue().libelle_produitProperty());
                c3.setVisible(true);
                c3.setText("Nom de la Catégorie");
                c3.setCellValueFactory(cellData -> cellData.getValue().nomCatProperty());
                c4.setVisible(true);
                c4.setText("Nom de la Sous-Catégorie");
                c4.setCellValueFactory(cellData -> cellData.getValue().nomSousCatProperty());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        this.link9.setOnAction((e) -> {
            try {
                dashBoardLabel.setText("Produits classés par fournisseur");
                getData(link9);
                c1.setText("Identifiant Produit");
                c1.setCellValueFactory(cellData -> cellData.getValue().id_produitProperty().asString());
                c2.setText("Nom du Produit");
                c2.setCellValueFactory(cellData -> cellData.getValue().libelle_produitProperty());
                c3.setVisible(true);
                c3.setText("Identifiant Fournisseur");
                c3.setCellValueFactory(cellData -> cellData.getValue().Id_fouProperty().asString());
                c4.setVisible(true);
                c4.setText("Nom du Fournisseur");
                c4.setCellValueFactory(cellData -> cellData.getValue().Nom_fouProperty());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        this.link1.setOnAction((e) -> {
            PopController popController = (PopController) popUp().getController();
            popController.setHyperlink(link1);
            populateViews(popController.getData());
            dashBoardLabel.setText("Chiffre d'affaire mensuel sur une année");
            c1.setText("Mois");
            c1.setCellValueFactory(cellData -> cellData.getValue().moisProperty().asString());
            c2.setText("Chiffre d'affaires mensuel");
            c2.setCellValueFactory(cellData -> cellData.getValue().caProperty().asString());
            c3.setVisible(false);
            c4.setVisible(false);
        });
        this.link2.setOnAction((e) -> {
            PopController popController = (PopController) popUp().getController();
            popController.setHyperlink(link2);
            populateViews(popController.getData());
            dashBoardLabel.setText("Top 10 des produits les plus commandées sur une année");
            c1.setText("Référence produit");
            c1.setCellValueFactory(cellData -> cellData.getValue().reference_produitProperty());
            c2.setText("Nom produit");
            c2.setCellValueFactory(cellData -> cellData.getValue().libelle_produitProperty());
            c3.setVisible(true);
            c3.setText("Quantité commandée");
            c3.setCellValueFactory(cellData -> cellData.getValue().qteProperty().asString());
            c4.setVisible(true);
            c4.setText("Fournisseur");
            c4.setCellValueFactory(cellData -> cellData.getValue().Nom_fouProperty());
        });
        this.link3.setOnAction((e) -> {
            PopController popController = (PopController) popUp().getController();
            popController.setHyperlink(link3);
            populateViews(popController.getData());
            dashBoardLabel.setText("Top 10 des produits les plus rémunérateurs sur une année");
            c1.setText("Référence produit");
            c1.setCellValueFactory(cellData -> cellData.getValue().reference_produitProperty());
            c2.setText("Nom produit");
            c2.setCellValueFactory(cellData -> cellData.getValue().libelle_produitProperty());
            c3.setVisible(true);
            c3.setText("Marge");
            c3.setCellValueFactory(cellData -> cellData.getValue().margeProperty().asObject().asString());
            c4.setVisible(true);
            c4.setText("Fournisseur");
            c4.setCellValueFactory(cellData -> cellData.getValue().Nom_fouProperty());
        });

    }

    public FXMLLoader popUp() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("../../com.villagegreen/Pop.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane));
        stage.show();
        return loader;
    }

    public void getData(Hyperlink hp) throws SQLException {
        try {
            if (hp.getId().equals(param4)) {
                ObservableList<Commande> viewData = DashBoardDAO.top10_clientca();
                populateViews(viewData);
            } else if (hp.getId().equals(param5)) {
                ObservableList<Commande> viewData = DashBoardDAO.top10_clientcomm();
                populateViews(viewData);
            } else if (hp.getId().equals(param6)) {
                ObservableList<Commande> viewData = DashBoardDAO.ca_typeclient();
                populateViews(viewData);
            } else if (hp.getId().equals(param7)) {
                ObservableList<Commande> viewData = DashBoardDAO.ca_fournisseur();
                populateViews(viewData);
            } else if (hp.getId().equals(param8)) {
                ObservableList<Commande> viewData = DashBoardDAO.prod_categ();
                populateViews(viewData);
            } else if (hp.getId().equals(param9)) {
                ObservableList<Commande> viewData = DashBoardDAO.prod_four();
                populateViews(viewData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    public void populateViews(ObservableList<Commande> viewData) { viewTable.setItems(viewData); }
}
