package com.example.sandra.gymapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class Tuperfil extends AppCompatActivity {

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

        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://fiery-inferno-9835.firebaseio.com/");
        TextView nom = (TextView)findViewById(R.id.nomPerfil);
        TextView numSoci = (TextView)findViewById(R.id.numSoci);
        TextView telefon = (TextView)findViewById(R.id.telfPerfil);
        TextView direccio = (TextView)findViewById(R.id.direccioPerfil);
        TextView email = (TextView)findViewById(R.id.email);







    }
}
