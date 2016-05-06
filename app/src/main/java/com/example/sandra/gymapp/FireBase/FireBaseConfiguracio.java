package com.example.sandra.gymapp.FireBase;

import android.content.Context;
import com.firebase.client.Firebase;

/**
 * Created by sandra on 06/05/2016.
 */
public class FireBaseConfiguracio {

    private String bbdd= "https://testgimmapp.firebaseio.com/";
    private Firebase ref;

    public FireBaseConfiguracio(){
        ref = new Firebase(bbdd);
    }
    public void configFirebase(Context context){
        Firebase.setAndroidContext(context);
    }
    public Firebase getRutinesCustomizades(){
        return ref.child("RutinasCustomizadas");
    }
    public Firebase getRutinesChat(){
        return ref.child("Chat");
    }
    public Firebase getClientes(){
        return ref.child("Clientes");
    }
    public Firebase getExercicis(){
        return ref.child("Exercicis");
    }
    public Firebase getIncidencias(){
        return ref.child("Incidencias");
    }
    public Firebase getInfoGym(){
        return ref.child("InfoGym");
    }
    public Firebase getMaquines(){
        return ref.child("Maquines");
    }
    public Firebase getRutinasStandard(){
        return ref.child("RutinasStandard");
    }
    public Firebase getSesions(){
        return ref.child("Sesions");
    }
}

