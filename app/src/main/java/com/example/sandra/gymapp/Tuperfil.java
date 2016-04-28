package com.example.sandra.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.sandra.gymapp.classesjava.Cliente;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

public class Tuperfil extends AppCompatActivity {
    private TextView nom;
    private TextView numSoci;
    private TextView telefon;
    private TextView direccio ;
    private TextView email;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuperfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent i = getIntent();
        uid = i.getStringExtra("uid");

        nom = (TextView)findViewById(R.id.nomPerfil);
        numSoci = (TextView)findViewById(R.id.numSoci);
        telefon = (TextView)findViewById(R.id.telfPerfil);
        direccio = (TextView)findViewById(R.id.direccioPerfil);
        email = (TextView)findViewById(R.id.emailPerfil);

        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://testgimmapp.firebaseio.com/");
        Firebase ref2 = ref.child("Clientes");

        Query queryRef = ref2.orderByChild("uid").equalTo(uid);

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Cliente a=  snapshot.getValue(Cliente.class);
                nom.setText(a.getNombre()+" "+a.getApellido());
                numSoci.setText(a.getnSocio());
                telefon.setText(a.getTelf());
                direccio.setText(a.getDireccion());
                email.setText(a.getEmail());
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });






    }
}
