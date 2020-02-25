package com.villagegreen.Controller;


import com.villagegreen.Modele.Client;
import com.villagegreen.Modele.ClientDAO;
import com.villagegreen.Modele.Produit;
import com.villagegreen.Modele.ProduitDAO;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import javax.swing.*;
import java.sql.SQLException;

public class ProduitsController {
    @FXML
    private TableView<Produit> produitTable;
    @FXML
    private TextField nomCliText;
    @FXML
    private TextField refProdText, libProdText, desProdText, idProdText, prixVProdText, prixAProdText, idRubText;

    @FXML
    private TableColumn<Produit, String> refProdColumn;
    @FXML
    private TableColumn<Produit, String> libProdColumn;
    @FXML
    private TableColumn<Produit, String> desProdColumn;
    @FXML
    private TableColumn<Produit, Integer> idProdColumn;
    @FXML
    private TableColumn<Produit, Double> prixVProdColumn;
    @FXML
    private TableColumn<Produit, Double> prixAProdColumn;
    @FXML
    private TableColumn<Produit, Integer> idRubColumn;
    @FXML
    private Button detailsBtn;
    @FXML
    private Label resultLabel;
    @FXML
    private Button deleteBtnP;

    private final ContextMenu contextMenu = new ContextMenu();

    @FXML
    public void populateProduits(ObservableList<Produit> cliData) { produitTable.setItems(cliData); }

    @FXML
    private void populateProduit(Produit prod) {
        ObservableList<Produit> prodData = FXCollections.observableArrayList();
        prodData.add(prod);
        produitTable.setItems(prodData);
    }

    /*@FXML
    public void setProdInfoToTextArea(Produit prod) {
        resultArea.setText("Nom: " + prod.getLibelle_produit());
    }*/

    @FXML
    public void populateAndShowClient(Produit prod) {
        if (prod != null) {
            populateProduit(prod);
            /*setProdInfoToTextArea(prod);*/
        } else {
            nomCliText.setText("Not found!");
        }
    }

    @FXML
    private void searchProduit() throws ClassNotFoundException, SQLException {
        try {
            Produit prod = ProduitDAO.searchProduit(nomCliText.getText());
            populateAndShowClient(prod);
        } catch (SQLException e) {
            e.printStackTrace();
            nomCliText.setText("Error " + e);
            throw e;
        }
    }

    @FXML
    private void searchProduits() throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Produit> prodData = ProduitDAO.searchProduits();
            populateProduits(prodData);
        } catch (SQLException e) {
            System.out.println("Error " + e);
            throw e;
        }
    }

    @FXML
    private void initialize() {
        idProdColumn.setCellValueFactory(cellData -> cellData.getValue().id_produitProperty().asObject());
        refProdColumn.setCellValueFactory(cellData -> cellData.getValue().reference_produitProperty());
        libProdColumn.setCellValueFactory(cellData -> cellData.getValue().libelle_produitProperty());
        desProdColumn.setCellValueFactory(cellData -> cellData.getValue().description_produitProperty());
        idProdColumn.setCellValueFactory(cellData -> cellData.getValue().id_produitProperty().asObject());
        prixVProdColumn.setCellValueFactory(cellData -> cellData.getValue().priV_produitProperty().asObject());
        prixAProdColumn.setCellValueFactory(cellData -> cellData.getValue().prixA_produitProperty().asObject());
        idRubColumn.setCellValueFactory(cellData -> cellData.getValue().id_rubriqueProperty().asObject());

        produitTable.setRowFactory(produitTableView -> {
            TableRow<Produit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                row.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(produitTable, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Produit cli = row.getItem();
                    /*displayCommandsByClient(cli);*/
                }
            });
            return row;
        });
        this.detailsBtn.setOnAction((e) -> {
                prefillForUpdate(getSelectedRow());
        });
        this.deleteBtnP.setOnAction(actionEvent ->{
            try{
                deleteProduit(getSelectedRow());
                clear();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        nomCliText.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                try {
                    searchProduit();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void clear() {
        this.refProdText.clear();
        this.libProdText.clear();
        this.desProdText.clear();
        this.idProdText.clear();
        this.prixVProdText.clear();
        this.prixAProdText.clear();
        this.idRubText.clear();
    }

    private Produit getSelectedRow() {
        Produit produit = produitTable.getSelectionModel().getSelectedItem();
        return produit;
    }

    private void prefillForUpdate(Produit prod) {
        String str = Integer.toString(prod.getId_produit());
        idProdText.setText(str);
        refProdText.setText(prod.getReference_produit());
        libProdText.setText(prod.getLibelle_produit());
        desProdText.setText(prod.getDescription_produit());
        idProdText.setText(prod.getId_produit().toString());
        prixVProdText.setText(Double.toString(prod.getPrixV_produit()));
        prixAProdText.setText(Double.toString(prod.getPrixA_produit()));
        idRubText.setText(Integer.toString(prod.getId_rubrique()));
    }

    @FXML
    public void deleteProduit(Produit produit) throws SQLException, ClassNotFoundException, NullPointerException {
        try {
            ProduitDAO.deleteProdWithId(produit.getId_produit());
            resultLabel.setText("Le produit " + libProdText.getText() + " a été supprimé!");
            resultLabel.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
            searchProduits();
        } catch (SQLException | NullPointerException e) {
            resultLabel.setText("Problem");
            resultLabel.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            throw e;
        }
    }

    @FXML
    public void updateNomProduit(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ProduitDAO.updateNomProd(refProdText.getText(), libProdText.getText(), desProdText.getText(), idProdText.getText(), prixVProdText.getText(), prixAProdText.getText(), idRubText.getText());
            resultLabel.setText("Le produit " + libProdText.getText() + " a été modifié!");
            resultLabel.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
            searchProduits();
            clear();
        } catch (SQLException e) {
            resultLabel.setText("Problem");
            resultLabel.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            throw e;
        }
    }

    @FXML
    public void insertProduit() throws SQLException, ClassNotFoundException {
        try {
            BackgroundFill backgroundFill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            if (!refProdText.getText().isEmpty() && !libProdText.getText().isEmpty() && !desProdText.getText().isEmpty() && !idProdText.getText().isEmpty() && !prixVProdText.getText().isEmpty() && !prixAProdText.getText().isEmpty() && !idRubText.getText().isEmpty()) {
                ProduitDAO.insertProd(refProdText.getText(), libProdText.getText(), desProdText.getText(), idProdText.getText(), prixVProdText.getText(), prixAProdText.getText(), idRubText.getText());
                resultLabel.setText("Le produit " + libProdText.getText() + " a été ajouté");
                resultLabel.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
                searchProduits();
                clear();
            } else {
                if (refProdText.getText().isEmpty()) {
                    refProdText.appendText("La référence doit être renseignée!");
                    refProdText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (libProdText.getText().isEmpty()) {
                    libProdText.appendText("Le libellé doit être renseigné!");
                    libProdText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (desProdText.getText().isEmpty()) {
                    desProdText.appendText("La description doit être renseignée!");
                    desProdText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (idProdText.getText().isEmpty()) {
                    idProdText.appendText("L'ID du produit doit être renseigné!");
                    idProdText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (prixVProdText.getText().isEmpty()) {
                    prixVProdText.appendText("Le prix de vente doit être renseigné!"+"\n");
                    prixVProdText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (prixAProdText.getText().isEmpty()) {
                    prixAProdText.appendText("Le prix d'achat doit être renseigné!"+"\n");
                    prixAProdText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                } else if (idRubText.getText().isEmpty()) {
                    idRubText.appendText("L'ID de la sous-catégorie doit être renseigné'!"+"\n");
                    idRubText.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                }
            }
        } catch (SQLException e) {
            resultLabel.setText("Problem");
            resultLabel.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            throw e;
        }
    }

}
