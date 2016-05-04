package com.example.sandra.gymapp.Rutina;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sandra.gymapp.ArrayAdapter.ArrayListAdapterExerciciRutina;
import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Exercici;
import com.example.sandra.gymapp.classesjava.RutinaStandard;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsRutina extends AppCompatActivity {
    Firebase ref;
    Firebase  infoGymRef;
    private RutinaStandard item;
    private ImageView imagetoolbar;
    private TextView nivell, temps, descrip;
    private ListView listEx;
    private ArrayList<Integer>exercicis;
    private ArrayList<Exercici>items= new ArrayList<>();;
    private CollapsingToolbarLayout toolbar_layout;
    private  ArrayListAdapterExerciciRutina itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_rutina);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(getBaseContext());
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        infoGymRef = ref.child("Exercicis");
        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        imagetoolbar = (ImageView)findViewById(R.id.image_appbar);
        nivell = (TextView)findViewById(R.id.nivelDetailRutina);
        temps = (TextView)findViewById(R.id.tempsRutinaDetail);
        descrip= (TextView)findViewById(R.id.descripRutinaDetail);
        listEx = (ListView)findViewById(R.id.listExerciciDetail);

        /**
         * Agafem el intent
         */
        Intent i = getIntent();
        item = (RutinaStandard) i.getSerializableExtra("item");
        exercicis = item.getExercicis();

        condiguracioDetails();
        configuracioLlista();
    }
    private void condiguracioDetails(){
        toolbar_layout.setTitle("  ");

        Picasso.with(getBaseContext())
                .load(item.getImage())
                .fit()
                .into(imagetoolbar);

        nivell.setText(item.getNivell());

        temps.setText(item.getTemps() + " min");

        descrip.setText(item.getDescripcio());


    }


    private void configuracioLlista(){
        int menu =1;
        for(int i =0; i< exercicis.size();i++) {
            Query queryRef = infoGymRef.orderByChild("id").equalTo(exercicis.get(i));

            queryRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                    Exercici a = snapshot.getValue(Exercici.class);
                    items.add(a);
                    System.out.println(items.size()+"-------------------"+a.getNom());
                    itemsAdapter = new ArrayListAdapterExerciciRutina(getBaseContext(), R.layout.list_exercici, items);
                    listEx.setAdapter(itemsAdapter);
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
}