package com.villagegreen.Modele;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;

public class NombreCommande {

        private IntegerProperty NbrComm;

        public NombreCommande() { this.NbrComm = new SimpleIntegerProperty(); }

        public int getNbrComm() { return NbrComm.get(); }

        public IntegerProperty NbrCommProperty() { return NbrComm; }

        public void setNbrComm(int NbrComm) { this.NbrComm.set(NbrComm); }
}
