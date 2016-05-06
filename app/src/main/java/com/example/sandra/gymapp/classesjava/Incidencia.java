package com.example.sandra.gymapp.classesjava;

/**
 * Created by 47419119l on 27/04/16.
 */
public class Incidencia {

    private String idMaquina;
    private String tipusIncidencia;
    private String incidencia;
    private String user;
    private boolean revisat;
    private String data;
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Incidencia(){

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(String idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getTipusIncidencia() {
        return tipusIncidencia;
    }

    public void setTipusIncidencia(String tipusIncidencia) {
        this.tipusIncidencia = tipusIncidencia;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

    public boolean isRevisat() {
        return revisat;
    }

    public void setRevisat(boolean revisat) {
        this.revisat = revisat;
    }

    public String toString(){
        return getIncidencia();
    }



}