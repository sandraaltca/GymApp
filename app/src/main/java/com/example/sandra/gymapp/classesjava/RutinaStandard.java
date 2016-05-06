package com.example.sandra.gymapp.classesjava;


import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sandra on 03/05/2016.
 */
public class RutinaStandard implements Serializable{

    private String id;
    private String nom;
    private int temps;
    private String nivell;
    private String descripcio;
    private String image;
    private ArrayList<Integer> exercicis;


    public RutinaStandard(){}

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

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public String getNivell() {
        return nivell;
    }

    public void setNivell(String nivell) {
        this.nivell = nivell;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Integer> getExercicis() {
        return exercicis;
    }

    public void setExercicis(ArrayList<Integer> exercicis) {
        this.exercicis = exercicis;
    }

}

