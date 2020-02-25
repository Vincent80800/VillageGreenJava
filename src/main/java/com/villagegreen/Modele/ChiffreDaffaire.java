package com.villagegreen.Modele;

import javafx.beans.property.*;

public class ChiffreDaffaire extends NombreCommande{
    private DoubleProperty ca;
    private IntegerProperty mois;
    private IntegerProperty qte;
    private DoubleProperty marge;

    public ChiffreDaffaire() {
        this.ca = new SimpleDoubleProperty();
        this.mois = new SimpleIntegerProperty();
        this.qte = new SimpleIntegerProperty();
        this.marge = new SimpleDoubleProperty();
    }

    public double getCa() { return ca.get(); }
    public DoubleProperty caProperty() { return ca; }
    public void setCa(double ca) { this.ca.set(ca); }

    public int getMois() { return mois.get(); }
    public IntegerProperty moisProperty() { return mois; }
    public void setMois(int mois) { this.mois.set(mois); }

    public int getQte() { return qte.get(); }
    public IntegerProperty qteProperty() { return qte; }
    public void setQte(int qte) { this.qte.set(qte); }

    public double getMarge() { return marge.get(); }
    public DoubleProperty margeProperty() { return marge; }
    public void setMarge(double marge) { this.marge.set(marge); }
}
