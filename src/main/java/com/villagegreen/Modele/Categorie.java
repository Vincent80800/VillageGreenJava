package com.villagegreen.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categorie extends Produit {
    private StringProperty nomCat;
    private StringProperty nomSousCat;
    private StringProperty catClient;

    public Categorie() {
        this.nomCat = new SimpleStringProperty();
        this.nomSousCat = new SimpleStringProperty();
        this.catClient = new SimpleStringProperty();
    }

    public String getNomCat() { return nomCat.get(); }
    public StringProperty nomCatProperty() {
        return nomCat;
    }
    public void setNomCat(String nomCat) {
        this.nomCat.set(nomCat);
    }

    public String getNomSousCat() {
        return nomSousCat.get();
    }
    public StringProperty nomSousCatProperty() {
        return nomSousCat;
    }
    public void setNomSousCat(String nomSousCat) {
        this.nomSousCat.set(nomSousCat);
    }

    public String getCatClient() { return catClient.get(); }
    public StringProperty catClientProperty() { return catClient; }
    public void setCatClient(String catClient) { this.catClient.set(catClient); }
}
