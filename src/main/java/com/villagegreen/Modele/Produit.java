package com.villagegreen.Modele;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.stage.Stage;

public class Produit extends ChiffreDaffaire{
    private StringProperty pro_ref;
    private StringProperty pro_lib;
    private StringProperty pro_des;
    private IntegerProperty pro_id;
    private DoubleProperty pro_priV;
    private DoubleProperty pro_priA;
    private IntegerProperty ru2_id;

    public Produit() {
        this.pro_ref = new SimpleStringProperty();
        this.pro_lib = new SimpleStringProperty();
        this.pro_des = new SimpleStringProperty();
        this.pro_id = new SimpleIntegerProperty();
        this.pro_priV = new SimpleDoubleProperty();
        this.pro_priA = new SimpleDoubleProperty();
        this.ru2_id = new SimpleIntegerProperty();
    }

    public String getReference_produit() {
        return pro_ref.get();
    }
    public StringProperty reference_produitProperty() {
        return pro_ref;
    }
    public void setReference_produit(String pro_ref) {
        this.pro_ref.set(pro_ref);
    }

    public String getLibelle_produit() { return pro_lib.get(); }
    public StringProperty libelle_produitProperty() { return pro_lib; }
    public void setLibelle_produit(String pro_lib) { this.pro_lib.set(pro_lib); }

    public String getDescription_produit() { return pro_des.get(); }
    public StringProperty description_produitProperty() { return pro_des; }
    public void setDescription_produit(String pro_des) { this.pro_des.set(pro_des); }

    public Integer getId_produit() { return pro_id.get(); }
    public IntegerProperty id_produitProperty() { return pro_id; }
    public void setId_produit(int pro_id) { this.pro_id.set(pro_id); }

    public Double getPrixV_produit() { return pro_priV.get(); }
    public DoubleProperty priV_produitProperty() { return pro_priV; }
    public void setPrixV_produit(Double pro_priV) { this.pro_priV.set(pro_priV); }

    public Double getPrixA_produit() {
        return pro_priA.get();
    }
    public DoubleProperty prixA_produitProperty() {
        return pro_priA;
    }
    public void setPrixA_produit(Double pro_priA) {
        this.pro_priA.set(pro_priA);
    }

    public int getId_rubrique() {
        return ru2_id.get();
    }
    public IntegerProperty id_rubriqueProperty() {
        return ru2_id;
    }
    public void setId_rubrique(int ru2_id) {
        this.ru2_id.set(ru2_id);
    }

}
