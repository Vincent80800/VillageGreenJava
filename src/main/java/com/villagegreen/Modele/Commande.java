package com.villagegreen.Modele;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.stage.Stage;

import java.util.Date;

public class Commande extends Client {
    private IntegerProperty id_commande;
    private DoubleProperty prix_total;
    private SimpleObjectProperty<Date> date_commande;
    private StringProperty statut_commande;
    private StringProperty adresse_facture;
    private StringProperty codePostal_facture;
    private StringProperty ville_facture;
    private StringProperty nom_client;
    private StringProperty prenom_client;

    public Commande() {
        this.id_commande = new SimpleIntegerProperty();
        this.prix_total = new SimpleDoubleProperty();
        this.date_commande = new SimpleObjectProperty<>();
        this.statut_commande = new SimpleStringProperty();
        this.adresse_facture = new SimpleStringProperty();
        this.codePostal_facture = new SimpleStringProperty();
        this.ville_facture = new SimpleStringProperty();
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
        return id_commande.get();
    }
    public IntegerProperty id_commandeProperty() {
        return id_commande;
    }
    public void setId_commande(int id_commande) {
        this.id_commande.set(id_commande);
    }

    public double getPrix_total() {
        return prix_total.get();
    }
    public DoubleProperty prix_totalProperty() {
        return prix_total;
    }
    public void setPrix_total(double prix_total) { this.prix_total.set(prix_total); }

    public Date getDate_commande() { return date_commande.get(); }
    public SimpleObjectProperty<Date> date_commandeProperty() { return date_commande; }
    public void setDate_commande(Date date_commande) { this.date_commande.set(date_commande); }

    public String getStatut_commande() {
        return statut_commande.get();
    }
    public StringProperty statut_commandeProperty() {
        return statut_commande;
    }
    public void setStatut_commande(String statut_commande) {
        this.statut_commande.set(statut_commande);
    }

    public String getAdresse_facture() {
        return adresse_facture.get();
    }
    public StringProperty adresse_factureProperty() {
        return adresse_facture;
    }
    public void setAdresse_facture(String adresse_facture) {
        this.adresse_facture.set(adresse_facture);
    }

    public String getCodePostal_facture() {
        return codePostal_facture.get();
    }
    public StringProperty codePostal_factureProperty() {
        return codePostal_facture;
    }
    public void setCodePostal_facture(String codePostal_facture) {
        this.codePostal_facture.set(codePostal_facture);
    }

    public String getVille_facture() {
        return ville_facture.get();
    }
    public StringProperty ville_factureProperty() {
        return ville_facture;
    }
    public void setVille_facture(String ville_facture) {
        this.ville_facture.set(ville_facture);
    }

}
