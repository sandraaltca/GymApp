package com.example.sandra.gymapp.classesjava;

import java.util.ArrayList;

/**
 * Created by 47419119l on 28/04/16.
 */
public class Maquina {

    private String id;
    private String descripcio;
    private String nom;
    private String dataInstalacio;
    private ArrayList<Step> steps;

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDataInstalacio() {
        return dataInstalacio;
    }

    public void setDataInstalacio(String dataInstalacio) {
        this.dataInstalacio = dataInstalacio;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
