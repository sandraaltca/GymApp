package com.example.sandra.gymapp.classesjava;


/**
 * Created by sandra on 03/05/2016.
 */
public class Exercici {
    private int id;
    private String nom;
    private String repeticions;
    private String image;
    private String descripcio;
    private String maquina;


    public Exercici (){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRepeticions() {
        return repeticions;
    }

    public void setRepeticions(String repeticions) {
        this.repeticions = repeticions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }
}