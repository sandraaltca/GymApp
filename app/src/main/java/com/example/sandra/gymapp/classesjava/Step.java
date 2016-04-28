package com.example.sandra.gymapp.classesjava;

/**
 * Created by 47419119l on 28/04/16.
 */
public class Step {

    private int nStep;
    private String descripcio;
    private String foto;
    private String anotacio;

    public Step(){}

    public int getnStep() {
        return nStep;
    }

    public void setnStep(int nStep) {
        this.nStep = nStep;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAnotacio() {
        return anotacio;
    }

    public void setAnotacio(String anotacio) {
        this.anotacio = anotacio;
    }
}
