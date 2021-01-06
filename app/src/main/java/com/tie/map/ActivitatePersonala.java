package com.tie.map;

import java.util.Date;

public class ActivitatePersonala {
    String data_adaugarii;
    int numar_kilograme;
    int numar_ore_sport;
    int numar_ore_odihna;
    int numar_calorii_consumate;
    double valoare_coeficient;

    public ActivitatePersonala(String data_adaugarii, int numar_kilograme, int numar_ore_sport, int numar_ore_odihna, int numar_calorii_consumate, double valoare_coeficient) {
        this.data_adaugarii = data_adaugarii;
        this.numar_kilograme = numar_kilograme;
        this.numar_ore_sport = numar_ore_sport;
        this.numar_ore_odihna = numar_ore_odihna;
        this.numar_calorii_consumate = numar_calorii_consumate;
        this.valoare_coeficient = valoare_coeficient;
    }

    public String getData_adaugarii() {
        return this.data_adaugarii;
    }

    public void setData_adaugarii(String data_adaugarii) {
        this.data_adaugarii = data_adaugarii;
    }

    public int getNumar_kilograme() {
        return numar_kilograme;
    }

    public void setNumar_kilograme(int numar_kilograme) {
        this.numar_kilograme = numar_kilograme;
    }

    public int getNumar_ore_sport() {
        return numar_ore_sport;
    }

    public void setNumar_ore_sport(int numar_ore_sport) {
        this.numar_ore_sport = numar_ore_sport;
    }

    public int getNumar_ore_odihna() {
        return numar_ore_odihna;
    }

    public void setNumar_ore_odihna(int numar_ore_odihna) {
        this.numar_ore_odihna = numar_ore_odihna;
    }

    public int getNumar_calorii_consumate() {
        return numar_calorii_consumate;
    }

    public void setNumar_calorii_consumate(int numar_calorii_consumate) {
        this.numar_calorii_consumate = numar_calorii_consumate;
    }

    public double getValoare_coeficient() {
        return valoare_coeficient;
    }

    public void setValoare_coeficient(double valoare_coeficient) {
        this.valoare_coeficient = valoare_coeficient;
    }

    @Override
    public String toString() {
        return "ActivitatePersonala{" +
                "data_adaugarii=" + data_adaugarii +
                ", numar_kilograme=" + numar_kilograme +
                ", numar_ore_sport=" + numar_ore_sport +
                ", numar_ore_odihna=" + numar_ore_odihna +
                ", numar_calorii_consumate=" + numar_calorii_consumate +
                ", valoare_coeficient=" + valoare_coeficient +
                '}';
    }
}
