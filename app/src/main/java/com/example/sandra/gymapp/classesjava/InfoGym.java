package com.example.sandra.gymapp.classesjava;


/**
 * Created by 47419119l on 28/04/16.
 */
public class InfoGym {

    int idGym;
    String nombreGym;
    String direccionGym;
    int telefonoGym;
    String correoElectronicoGym;
    long latitudGym;
    long longitudGym;
    String horarioGym[];

    public int getIdGym() {
        return idGym;
    }

    public void setIdGym(int idGym) {
        this.idGym = idGym;
    }
    public String getNombreGym() {
        return nombreGym;
    }

    public void setNombreGym(String nombreGym) {
        this.nombreGym = nombreGym;
    }

    public String getDireccionGym() {
        return direccionGym;
    }

    public void setDireccionGym(String direccionGym) {
        this.direccionGym = direccionGym;
    }

    public int getTelefonoGym() {
        return telefonoGym;
    }

    public void setTelefonoGym(int telefonoGym) {
        this.telefonoGym = telefonoGym;
    }

    public String getCorreoElectronicoGym() {
        return correoElectronicoGym;
    }

    public void setCorreoElectronicoGym(String correoElectronicoGym) {
        this.correoElectronicoGym = correoElectronicoGym;
    }

    public long getLatitudGym() {
        return latitudGym;
    }

    public void setLatitudGym(long latitudGym) {
        this.latitudGym = latitudGym;
    }

    public long getLongitudGym() {
        return longitudGym;
    }

    public void setLongitudGym(long longitudGym) {
        this.longitudGym = longitudGym;
    }

    public String[] getHorarioGym() {
        return horarioGym;
    }

    public void setHorarioGym(String[] horarioGym) {
        this.horarioGym = horarioGym;
    }
}