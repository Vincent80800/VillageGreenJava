package com.villagegreen.Controller;

import com.villagegreen.Modele.Commande;
import com.villagegreen.Modele.DashBoardDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.sql.SQLException;

public class PopController {
    @FXML
    private TextField year = new TextField();
    @FXML
    private Button val = new Button();
    @FXML
    private Label labReq;

    private String  param1 = "link1",
                    param2 = "link2",
                    param3 = "link3";

    ObservableList<Commande> data1 = FXCollections.observableArrayList();

    Hyperlink hyperlink = new Hyperlink();

    @FXML
    private void initialize() {
        val.setOnAction(e -> {
            searchData();
        });

        year.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
               searchData();
            }
        });
    }

    public void setHyperlink(Hyperlink hyper){
        hyperlink = hyper;
    }

    public Hyperlink getHyperlink() {
        return this.hyperlink;
    }

    public void searchData() {
        if (getHyperlink().getId().equals(param1)) {
            try {
                setData(DashBoardDAO.CA_Mensuel(year.getText()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if(getHyperlink().getId().equals(param2)) {
            try {
                setData(DashBoardDAO.Top10_ProdCommande(year.getText()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }  else if(getHyperlink().getId().equals(param3)) {
            try {
                setData(DashBoardDAO.Top10_ProdRemunerateur(year.getText()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        Stage stage = (Stage) val.getScene().getWindow();
        stage.close();
    }

    public void setData(ObservableList<Commande> data) {
        data1.addAll(data);
    }

    public ObservableList<Commande> getData() {
        return data1;
    }

}
