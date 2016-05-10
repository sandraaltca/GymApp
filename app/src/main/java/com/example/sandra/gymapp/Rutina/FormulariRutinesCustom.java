package com.example.sandra.gymapp.Rutina;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandra.gymapp.MainActivity;
import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Exercici;
import com.example.sandra.gymapp.classesjava.RutinaCustomize;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FormulariRutinesCustom extends AppCompatActivity {
    private ArrayList<Integer> exercicis;
    private Firebase ref;
    private Firebase  infoGymRef;
    private FloatingActionButton afeguirExercici;
    private ListView listExercici;
    private Spinner nivellRutina;
    private EditText nomRutina, tempsRutina, descripcioRutina;
    private String uidUser= MainActivity.uid;
    private Firebase RutinaCustomizada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulari_rutines_custom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        afeguirExercici= (FloatingActionButton) findViewById(R.id.fab);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Firebase.setAndroidContext(getBaseContext());

        nivellRutina = (Spinner) findViewById(R.id.spinnerNivell);
        exercicis = new ArrayList<>();
        listExercici = (ListView) findViewById(R.id.listExerciciForm);
        nomRutina =(EditText) findViewById(R.id.rutinaNomForm);
        tempsRutina =(EditText)findViewById(R.id.tiempoRutinaForm);
        descripcioRutina = (EditText)findViewById(R.id.descripRutinaForm);
        configFirebase();
        configurarLlista();
        buttonCOnfig();
    }

    /**
     * Metode per configurar floatingButton.
     */
    private void buttonCOnfig(){
        afeguirExercici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subirRutina();
                nomRutina.setText("");
                descripcioRutina.setText("");
                tempsRutina.setText("");
                exercicis.clear();
                Toast.makeText(getBaseContext(), "Rutina guardada!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Metode configuració firebase
     */
    private void configFirebase(){
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        infoGymRef = ref.child("Exercicis");
    }

    /**
     * Metode configurar llista de exercicis
     */
    private void configurarLlista(){
        final FirebaseListAdapter adapter = new FirebaseListAdapter<Exercici>(this, Exercici.class, R.layout.list_exercici, infoGymRef) {
            @Override
            protected void populateView(View v, Exercici info, int position) {

                TextView nom = (TextView) v.findViewById(R.id.nomExercici);
                TextView temps = (TextView)v.findViewById(R.id.repeticionsEx);
                ImageView fotoExercici=(ImageView)v.findViewById(R.id.fotoExercici);

                nom.setText(info.getNom());
                temps.setText(info.getRepeticions());
                Picasso.with(getBaseContext())
                        .load(info.getImage())
                        .fit()
                        .into(fotoExercici);
            }
        };
        listExercici.setAdapter(adapter);

        listExercici.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createAndShowAlertDialog((Exercici)adapter.getItem(position));
            }
        });
    }

    /**
     * Dialeg per a saber si el usuari està segur de afeguir el exercici a la rutina.
     * @param exercici Exercici el qual em sel·lecionat.
     */
    private void createAndShowAlertDialog(final Exercici exercici) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿ Quieres añadir este ejercicio ?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                exercicis.add(exercici.getId());
                Toast.makeText(getBaseContext(), "Ejercicio añadido!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Metode per pujar rutines a firebase.
     */
    private void subirRutina(){

        RutinaCustomizada = ref.child("RutinasCustomizadas");
        RutinaCustomize customize = new RutinaCustomize();
        if(nomRutina.getText().toString().equals("")||descripcioRutina.getText().toString().equals("")||tempsRutina.getText().toString().equals("")){
            Toast.makeText(getBaseContext(), "Faltan campos por rellenar", Toast.LENGTH_LONG).show();
        }else {
            customize.setNom(nomRutina.getText().toString());
            customize.setTemps(Integer.parseInt(tempsRutina.getText().toString()));
            String nivellstring = String.valueOf(nivellRutina.getSelectedItem());
            customize.setNivell(nivellstring);
            customize.setDescripcio(descripcioRutina.getText().toString());
            customize.setImage("http://www.cambiatufisico.com/wp-content/uploads/gimnasio5.jpg");
            customize.setExercicis(exercicis);
            customize.setUidUser(uidUser);

            Firebase client = RutinaCustomizada.push();
            client.setValue(customize);
        }
    }

}
