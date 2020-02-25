package com.villagegreen.Modele;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.stage.Stage;

public class Fournisseur extends Categorie {

    private StringProperty Nom_fou;
    private IntegerProperty Id_fou;

    public Fournisseur() {
        this.Nom_fou = new SimpleStringProperty();
        this.Id_fou = new SimpleIntegerProperty();
    }

    public String getNom_fou() { return Nom_fou.get(); }
    public StringProperty Nom_fouProperty() { return Nom_fou; }
    public void setNom_fou(String fou) { this.Nom_fou.set(fou); }

    public Integer getId_fou() { return Id_fou.get(); }
    public IntegerProperty Id_fouProperty() { return Id_fou; }
    public void setId_fou(int Id_fou) { this.Id_fou.set(Id_fou); }
}
