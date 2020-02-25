package com.villagegreen.Modele;

import javafx.beans.property.*;


public class Client extends Fournisseur {

        private StringProperty cli_nom;
        private StringProperty cli_pre;
        private StringProperty cli_adr;
        private StringProperty cli_email;
        private StringProperty cli_tel;
        private IntegerProperty cli_id;
        private IntegerProperty ven_id;

        public Client() {
            this.cli_nom = new SimpleStringProperty();
            this.cli_pre = new SimpleStringProperty();
            this.cli_adr = new SimpleStringProperty();
            this.cli_email = new SimpleStringProperty();
            this.cli_tel = new SimpleStringProperty();
            this.cli_id = new SimpleIntegerProperty();
            this.ven_id = new SimpleIntegerProperty();
        }

        public String getNom_client() {
            return cli_nom.get();
        }

        public StringProperty nom_clientProperty() {
            return cli_nom;
        }

        public void setNom_client(String cli_nom) {
            this.cli_nom.set(cli_nom);
        }

        public String getPrenom_client() {
            return cli_pre.get();
        }

        public StringProperty prenom_clientProperty() {
            return cli_pre;
        }

        public void setPrenom_client(String cli_pre) {
            this.cli_pre.set(cli_pre);
        }

        public String getAdresse_client() {
            return cli_adr.get();
        }

        public StringProperty adresse_clientProperty() {
            return cli_adr;
        }

        public void setAdresse_client(String cli_adr) {
            this.cli_adr.set(cli_adr);
        }

        public String getMail_client() {
            return cli_email.get();
        }

        public StringProperty mail_clientProperty() {
            return cli_email;
        }

        public void setMail_client(String cli_email) {
            this.cli_email.set(cli_email);
        }

        public String getTel_client() {
            return cli_tel.get();
        }

        public StringProperty tel_clientProperty() {
            return cli_tel;
        }

        public void setTel_client(String cli_tel) {
            this.cli_tel.set(cli_tel);
        }

        public int getId_client() {
            return cli_id.get();
        }

        public IntegerProperty id_clientProperty() {
            return cli_id;
        }

        public void setId_client(int cli_id) {
            this.cli_id.set(cli_id);
        }

        public int getId_vendeur() {
            return ven_id.get();
        }

        public IntegerProperty id_vendeurProperty() {
            return ven_id;
        }

        public void setId_vendeur(int ven_id) {
            this.ven_id.set(ven_id);
        }
    }
