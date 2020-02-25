package com.villagegreen.Modele;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.stage.Stage;

import java.util.Date;

public class Commande extends Client {
    private IntegerProperty com_id;
    private DoubleProperty com_tot;
    private SimpleObjectProperty<Date> com_dat;
    private StringProperty com_eta;
    private StringProperty fac_adr;
    private StringProperty fac_cp;
    private StringProperty fac_vil;
    private StringProperty nom_client;
    private StringProperty prenom_client;

    public Commande() {
        this.com_id = new SimpleIntegerProperty();
        this.com_tot = new SimpleDoubleProperty();
        this.com_dat = new SimpleObjectProperty<>();
        this.com_eta = new SimpleStringProperty();
        this.fac_adr = new SimpleStringProperty();
        this.fac_cp = new SimpleStringProperty();
        this.fac_vil = new SimpleStringProperty();
        this.nom_client = new SimpleStringProperty();
        this.prenom_client = new SimpleStringProperty();
    }

    public String getNom_client() {
        return nom_client.get();
    }
    public StringProperty nom_clientProperty() {
        return nom_client;
    }
    public void setNom_client(String nom_client) {
        this.nom_client.set(nom_client);
    }

    public String getPrenom_client() {
        return prenom_client.get();
    }
    public StringProperty prenom_clientProperty() {
        return prenom_client;
    }
    public void setPrenom_client(String prenom_client) {
        this.prenom_client.set(prenom_client);
    }

    public int getId_commande() {
        return com_id.get();
    }
    public IntegerProperty id_commandeProperty() {
        return com_id;
    }
    public void setId_commande(int id_commande) {
        this.com_id.set(id_commande);
    }

    public double getPrix_total() {
        return com_tot.get();
    }
    public DoubleProperty prix_totalProperty() {
        return com_tot;
    }
    public void setPrix_total(double prix_total) { this.com_tot.set(prix_total); }

    public Date getDate_commande() { return com_dat.get(); }
    public SimpleObjectProperty<Date> date_commandeProperty() { return com_dat; }
    public void setDate_commande(Date date_commande) { this.com_dat.set(date_commande); }

    public String getStatut_commande() {
        return com_eta.get();
    }
    public StringProperty statut_commandeProperty() {
        return com_eta;
    }
    public void setStatut_commande(String statut_commande) {
        this.com_eta.set(statut_commande);
    }

    public String getAdresse_facture() {
        return fac_adr.get();
    }
    public StringProperty adresse_factureProperty() {
        return fac_adr;
    }
    public void setAdresse_facture(String adresse_facture) {
        this.fac_adr.set(adresse_facture);
    }

    public String getCodePostal_facture() {
        return fac_cp.get();
    }
    public StringProperty codePostal_factureProperty() {
        return fac_cp;
    }
    public void setCodePostal_facture(String codePostal_facture) {
        this.fac_cp.set(codePostal_facture);
    }

    public String getVille_facture() {
        return fac_vil.get();
    }
    public StringProperty ville_factureProperty() {
        return fac_vil;
    }
    public void setVille_facture(String ville_facture) {
        this.fac_vil.set(ville_facture);
    }

}
