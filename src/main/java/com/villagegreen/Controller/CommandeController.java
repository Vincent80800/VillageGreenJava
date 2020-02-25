package com.villagegreen.Controller;

import com.villagegreen.Modele.*;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.Date;

public class CommandeController{
    @FXML
    private TableView comsTable;
    @FXML
    private TableColumn<Commande, Integer> comIdCol;
    @FXML
    private TableColumn<Commande, Date> comDateCol;
    @FXML
    private TableColumn<Commande, String> comStatCol;
    @FXML
    private TableColumn<Commande, String> comAdrFacCol;
    @FXML
    private TableColumn<Commande, String> comCPFacCol;
    @FXML
    private TableColumn<Commande, String> comVilFacCol;
    @FXML
    private TableColumn<Commande, Double> comTotCol;

    @FXML
    private Label labelCommande;
    @FXML
    private Button closeButton;


    @FXML
    public void searchCommandeByClient(Client cli) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Commande> com = CommandeDAO.searchCommandeByCli(cli.getId_client());
            populateAndShowCommande(com);
            labelCommande.setText("Commandes de Monsieur/Madame " + cli.getPrenom_client() + " " + cli.getNom_client());
        } catch (SQLException e) {
            System.out.println("Error" + e);
            throw e;
        }
    }


    @FXML
    public void populateCommande(ObservableList<Commande> com) {
        ObservableList<Commande> comData = FXCollections.observableArrayList();
        comData.addAll(com);
        comsTable.setItems(comData);
    }

    @FXML
    public void populateAndShowCommande(ObservableList<Commande> com) {
        if (com != null) {
            populateCommande(com);
        } else {
            System.out.println("Error");
        }
    }

    @FXML
    private void initialize() {
        comIdCol.setCellValueFactory(cellData -> cellData.getValue().id_commandeProperty().asObject());
        comIdCol.setCellValueFactory(cellData -> cellData.getValue().id_commandeProperty().asObject());
        comDateCol.setCellValueFactory(cellData -> cellData.getValue().date_commandeProperty());
        comStatCol.setCellValueFactory(cellData -> cellData.getValue().statut_commandeProperty());
        comAdrFacCol.setCellValueFactory(cellData -> cellData.getValue().adresse_factureProperty());
        comCPFacCol.setCellValueFactory(cellData -> cellData.getValue().codePostal_factureProperty());
        comVilFacCol.setCellValueFactory(cellData -> cellData.getValue().ville_factureProperty());
        comTotCol.setCellValueFactory(cellData -> cellData.getValue().prix_totalProperty().asObject());

    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void searchCommandes() throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Commande> commData = CommandeDAO.searchCommandes();
            populateCommande(commData);
            labelCommande.setText("Toutes les commandes ");
        } catch (SQLException e) {
            System.out.println("Error " + e);
            throw e;
        }
    }
}
