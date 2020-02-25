package com.villagegreen.Controller;

import com.google.gson.*;

import com.villagegreen.App;
import com.villagegreen.Modele.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ClientsController {

    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TextField nomCliText, prenomCliText, adresseCliText, mailCliText, telCliText, idCliText, idVenText;

    @FXML
    private TableColumn<Client, String> cliNomColumn;
    @FXML
    private TableColumn<Client, String> cliPrenomColumn;
    @FXML
    private TableColumn<Client, String> cliAdresseColumn;
    @FXML
    private TableColumn<Client, String> cliMailColumn;
    @FXML
    private TableColumn<Client, String> cliTelColumn;
    @FXML
    private TableColumn<Client, Integer> cliIdColumn;
    @FXML
    private TableColumn<Client, Integer> venIdColumn;

    @FXML
    private Button detailsBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Label resultLabel;
    @FXML
    private Button showCommande;
    @FXML
    private Button updateCliBtn;
    @FXML
    private Button insertBtn;
    @FXML
    private Button clearBtn;



    private final ContextMenu contextMenu = new ContextMenu();


    @FXML
    public void populateClients(ObservableList<Client> cliData) {
        clientTable.setItems(cliData);
    }


    @FXML
    private void searchClients() throws ClassNotFoundException, SQLException, IOException {
        try {
            detailsBtn.setDisable(true);
            showCommande.setDisable(true);
            ObservableList<Client> cliData = ClientDAO.getAllClients();
            populateClients(cliData);
        } catch (SQLException | IOException e) {
            System.out.println("Error " + e);
            throw e;
        }
    }

    @FXML
    private void initialize() {
        cliIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Client, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Client, Integer> cellData) {
                return cellData.getValue().id_clientProperty().asObject();
            }
        });

        cliNomColumn.setCellValueFactory(cellData -> cellData.getValue().nom_clientProperty());
        cliPrenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenom_clientProperty());
        cliAdresseColumn.setCellValueFactory(cellData -> cellData.getValue().adresse_clientProperty());
        cliMailColumn.setCellValueFactory(cellData -> cellData.getValue().mail_clientProperty());
        cliTelColumn.setCellValueFactory(cellData -> cellData.getValue().tel_clientProperty());
        cliIdColumn.setCellValueFactory(cellData -> cellData.getValue().id_clientProperty().asObject());
        venIdColumn.setCellValueFactory(cellData -> cellData.getValue().id_vendeurProperty().asObject());

        clientTable.setRowFactory(clientTableView -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                showCommande.setDisable(false);
                detailsBtn.setDisable(false);
                row.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(clientTable, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Client cli = row.getItem();
                    displayCommandsByClient(cli);
                }
            });
            return row;
        });

        this.showCommande.setOnAction((e) -> {
            displayCommandsByClient(getSelectedRow());
        });

        this.detailsBtn.setOnAction((e) -> {
            updateCliBtn.setDisable(false);
            deleteBtn.setDisable(false);
            insertBtn.setDisable(true);
            clearBtn.setDisable(false);
            prefillForUpdate(getSelectedRow());
        });
        this.deleteBtn.setOnAction(actionEvent ->{
            try{
                updateCliBtn.setDisable(false);
                deleteClient(getSelectedRow());
                clear();
            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void clear() {
        this.nomCliText.clear();
        this.prenomCliText.clear();
        this.adresseCliText.clear();
        this.mailCliText.clear();
        this.telCliText.clear();
        this.idCliText.clear();
        this.idVenText.clear();
        insertBtn.setDisable(false);
        updateCliBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }

    private Client getSelectedRow() {
        Client client = clientTable.getSelectionModel().getSelectedItem();
        return client;
    }

    private void prefillForUpdate(Client cli) {
        String str = Integer.toString(cli.getId_client());
        idCliText.setText(str);
        nomCliText.setText(cli.getNom_client());
        prenomCliText.setText(cli.getPrenom_client());
        adresseCliText.setText(cli.getAdresse_client());
        mailCliText.setText(cli.getMail_client());
        telCliText.setText(cli.getTel_client());
        idCliText.setText(Integer.toString(cli.getId_client()));
        idVenText.setText(Integer.toString(cli.getId_vendeur()));
    }

    @FXML
    public void deleteClient(Client client) throws SQLException, ClassNotFoundException, NullPointerException, IOException {
        try {
            ClientDAO.deleteCliWithId(client.getId_client());
            resultLabel.setText("Le profil a été supprimé!");
            resultLabel.setTextFill(Paint.valueOf("green"));
            searchClients();
            deleteBtn.setDisable(true);
            updateCliBtn.setDisable(true);
        } catch (SQLException | NullPointerException | IOException e) {
            resultLabel.setText("Suppression du client impossible");
            resultLabel.setTextFill(Paint.valueOf("red"));
            throw e;
        }
    }

    @FXML
    public void updateNomClient(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        try {
            ClientDAO.updateNomCli(nomCliText.getText(), prenomCliText.getText(), adresseCliText.getText(), mailCliText.getText(), telCliText.getText(), idCliText.getText(), idVenText.getText());
            resultLabel.setText("Le profil de " + prenomCliText.getText() + " " + nomCliText.getText() + " a été modifié!");
            resultLabel.setTextFill(Paint.valueOf("green"));
            searchClients();
            clear();
        } catch (SQLException | IOException e) {
            resultLabel.setText("Mis à jour client impossible");
            resultLabel.setTextFill(Paint.valueOf("red"));
            throw e;
        }
    }

    @FXML
    public void insertClient() throws SQLException, ClassNotFoundException, IOException {
        try {
            BackgroundFill backgroundFill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            if (!nomCliText.getText().isEmpty() && !prenomCliText.getText().isEmpty() && !adresseCliText.getText().isEmpty() && !mailCliText.getText().isEmpty() && !telCliText.getText().isEmpty() && !idCliText.getText().isEmpty() && !idVenText.getText().isEmpty()) {
                ClientDAO.insertCli(nomCliText.getText(), prenomCliText.getText(), adresseCliText.getText(), mailCliText.getText(), telCliText.getText(), idCliText.getText(), idVenText.getText());
                resultLabel.setText("Le client " + nomCliText.getText() + " " + prenomCliText.getText() + " a été ajouté");
                resultLabel.setTextFill(Paint.valueOf("green"));
                searchClients();
                clear();
            } else {
                if (nomCliText.getText().isEmpty()) {
                    nomCliText.appendText("Le nom doit être renseignée!");
                    nomCliText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (prenomCliText.getText().isEmpty()) {
                    prenomCliText.appendText("Le prénom doit être renseigné!");
                    prenomCliText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (adresseCliText.getText().isEmpty()) {
                    adresseCliText.appendText("L'adresse doit être renseignée!");
                    adresseCliText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (mailCliText.getText().isEmpty()) {
                    mailCliText.appendText("L'adresse Email doit être renseignée!");
                    mailCliText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (telCliText.getText().isEmpty()) {
                    telCliText.appendText("Le numéro de téléphone doit être renseigné!"+"\n");
                    telCliText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (idCliText.getText().isEmpty()) {
                    idCliText.appendText("L'Id du client doit être renseigné!"+"\n");
                    idCliText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (idVenText.getText().isEmpty()) {
                    idVenText.appendText("L'ID du commercial doit être renseigné'!"+"\n");
                    idVenText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                }
            }
        } catch (SQLException | IOException e) {
            resultLabel.setText("Insertion du client impossible");
            resultLabel.setTextFill(Paint.valueOf("red"));
            throw e;
        }
    }

    private void displayCommandsByClient(Client cli) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/com.villagegreen/CommandeView.fxml"));
            Pane commandsView = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(commandsView));
            CommandeController controller = loader.getController();
            controller.searchCommandeByClient(cli);
            stage.show();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
