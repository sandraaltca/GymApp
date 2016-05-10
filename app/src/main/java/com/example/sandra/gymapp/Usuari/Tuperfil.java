package com.example.sandra.gymapp.Usuari;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Cliente;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

public class Tuperfil extends AppCompatActivity {
    private Firebase ref;
    private TextView nom;
    private TextView numSoci;
    private TextView telefon;
    private TextView direccio ;
    private TextView email;
    private String uid;
    private Firebase ref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuperfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        /**
         * Extreiem el intent
         */
        Intent i = getIntent();
        uid = i.getStringExtra("uid");
        /**
         * Instanciem el element del layout.
         */
        nom = (TextView)findViewById(R.id.nomPerfil);
        numSoci = (TextView)findViewById(R.id.numSoci);
        telefon = (TextView)findViewById(R.id.telfPerfil);
        direccio = (TextView)findViewById(R.id.direccioPerfil);
        email = (TextView)findViewById(R.id.emailPerfil);
        /**
         * Referencia firebase.
         */
        Firebase.setAndroidContext(this);

        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        ref2 = ref.child("Clientes");

        queryUsuari();
        /**
         * Configurar el fab.
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogChangePass();
            }
        });


    }
    /**
     * Metode amb query per trobar el usuari del qual estem fent la consulta ( La uid es única)
     */
    private  void queryUsuari(){
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
    /**
     * Metode que que mostra un dialeg per a poder modificar la contrasenya del usuari.
     */
    public void DialogChangePass(){

        LayoutInflater factory = LayoutInflater.from(this);

        final View textEntryView = factory.inflate(R.layout.text_entry, null);

        final EditText input1 = (EditText) textEntryView.findViewById(R.id.editText);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.editText2);


        //input1.setText("DefaultValue", TextView.BufferType.EDITABLE);
        //input2.setText("DefaultValue", TextView.BufferType.EDITABLE);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.mipmap.key).setTitle(
                "Modifique su contraseña").setView(
                textEntryView).setPositiveButton("Modificar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        
                        ref.changePassword(email.getText().toString(), input1.getText().toString(), input2.getText().toString(), new Firebase.ResultHandler() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(getBaseContext(), "Constraseña modificada", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                Toast.makeText(getBaseContext(), "Error al modificar.", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                }).setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                    }
                });
        alert.show();
    }
}
