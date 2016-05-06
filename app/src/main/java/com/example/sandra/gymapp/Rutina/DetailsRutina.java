package com.example.sandra.gymapp.Rutina;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sandra.gymapp.ArrayAdapter.ArrayListAdapterExerciciRutina;
import com.example.sandra.gymapp.FireBase.FireBaseConfiguracio;
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
import java.util.Calendar;

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
    private String nomRutina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_rutina);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FireBaseConfiguracio fireBaseConfiguracio = new FireBaseConfiguracio();
        fireBaseConfiguracio.configFirebase(getBaseContext());
        infoGymRef    =fireBaseConfiguracio.getExercicis();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEventToCalendar();
            }
        });

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
        String id = i.getStringExtra("id");
        exercicis = item.getExercicis();
        if(id.equals("standard")) {
            condiguracioDetailsStandard();
        }else{

        }
        configuracioLlista();
    }
    private void condiguracioDetailsStandard(){
        toolbar_layout.setTitle("  ");

        Picasso.with(getBaseContext())
                .load(item.getImage())
                .fit()
                .into(imagetoolbar);

        nivell.setText(item.getNivell());

        temps.setText(item.getTemps() + " min");

        descrip.setText(item.getDescripcio());

        nomRutina = item.getNom();


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
    private void addEventToCalendar(){
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, 29);
        cal.set(Calendar.MONTH, 4);
        cal.set(Calendar.YEAR, 2013);

        cal.set(Calendar.HOUR_OF_DAY, 22);
        cal.set(Calendar.MINUTE, 45);

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis()+60*60*1000);

        intent.putExtra(CalendarContract.Events.ALL_DAY, false);
        intent.putExtra(CalendarContract.Events.RRULE , "FREQ=DAILY");
        intent.putExtra(CalendarContract.Events.TITLE, nomRutina);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, descrip.getText().toString());
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,"GimAPP");

        startActivity(intent);
    }
}