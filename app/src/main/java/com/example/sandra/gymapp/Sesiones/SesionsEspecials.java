package com.example.sandra.gymapp.Sesiones;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sandra.gymapp.ArrayAdapter.ArrayListAdapterSesions;
import com.example.sandra.gymapp.Dialog.DateDialog;
import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Sesion;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class SesionsEspecials extends Fragment implements DatePickerDialog.OnDateSetListener {

    private Firebase sesionRef, ref;
    private ListView listSesiones;
    private TextView nom , data, monitor, dateText;
    private ImageButton step,bcombat, bpump,fitnes,zumba, btnDate;
    private ImageView fotsesio;
    private boolean sesio =false ,databo = false;
    private String sesioNom ="";
    private ArrayList<Sesion> items;
    private int dia , mes , any;
    public SesionsEspecials() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sesions_especials, container, false);


        items = new ArrayList<Sesion>();
        /**
         * Configuració Firebase
         */
        Firebase.setAndroidContext(getContext());
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        sesionRef = ref.child("Sesions");
        /**
         * Instanciem obejctes
         */
        listSesiones = (ListView)rootView.findViewById(R.id.listSesions);
        dateText = (TextView)rootView.findViewById(R.id.dateText);
        zumba =(ImageButton)rootView.findViewById(R.id.sesio1);
        bcombat=(ImageButton)rootView.findViewById(R.id.sesio2);
        step=(ImageButton)rootView.findViewById(R.id.sesio3);
        bpump =(ImageButton)rootView.findViewById(R.id.sesio4);
        fitnes=(ImageButton)rootView.findViewById(R.id.sesio5);
        btnDate=(ImageButton)rootView.findViewById(R.id.dataPic);

        configuracioLlistaMaquines();
        configuraciobuttons();
        configDataStart();


        return rootView;
    }

    /**
     * Metode que crida a el dialeg del picker.
     */
    public void canviarData() {
        Date data = new Date();
        DateDialog dialogoFecha = new DateDialog();
        dialogoFecha.setOnDateSetListener(this);
        Bundle args = new Bundle();
        //Enviem la data actual al picker.
        args.putLong("fecha", data.getTime());
        dialogoFecha.setArguments(args);
        //
        dialogoFecha.show(getActivity().getSupportFragmentManager(), "selectorFecha");

    }
    /**
     * Metode per configurar els butons.
     */
    private void configuraciobuttons(){
        zumba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesioNom="Zumba";
                if(databo){
                    consultarPerSesioData();
                }else{
                    items.clear();
                    configuracioLlistaMaquinesSesions("Zumba");
                    sesio=true;
                }


            }
        });
        bcombat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesioNom = "Body Combat";
                if (databo) {
                    consultarPerSesioData();
                } else {
                    items.clear();
                    configuracioLlistaMaquinesSesions("Body Combat");
                    sesio = true;
                }

            }
        });
        step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesioNom="Step";
                if(databo){
                    consultarPerSesioData();
                }else {
                    items.clear();
                    configuracioLlistaMaquinesSesions("Step");
                    sesio = true;
                }

            }
        });
        bpump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesioNom = "Body pump";
                if (databo) {
                    consultarPerSesioData();
                } else {
                    items.clear();
                    configuracioLlistaMaquinesSesions("Body pump");
                    sesio = true;
                }
            }
        });

        fitnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesioNom = "Fitness";
                if (databo) {
                    consultarPerSesioData();
                } else {
                    items.clear();
                    configuracioLlistaMaquinesSesions("Fitness");
                    sesio = true;
                }

            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                canviarData();
            }
        });

    }

    /**
     * Configurar data actual al iniciar.
     */
    private void configDataStart(){
        Date date = new Date();
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        dateText.setText(dt1.format(date).toString());
    }
    /**
     * Metode per llistar TOTES les sesions realitzades al centre.
     */
    private void configuracioLlistaMaquines(){
        databo =false;
        sesio=false;
        FirebaseListAdapter adapter = new FirebaseListAdapter<Sesion>(getActivity(), Sesion.class, R.layout.list_sesions_calendari, sesionRef) {
            @Override
            protected void populateView(View v, Sesion info, int position) {
                nom = (TextView) v.findViewById(R.id.nomSesio);
                data = (TextView) v.findViewById(R.id.dataHorariSesio);
                monitor = (TextView) v.findViewById(R.id.NomMonitora);
                fotsesio = (ImageView) v.findViewById(R.id.fotoSesio);

                nom.setText(info.getNom());
                data.setText(info.getData() + " " + info.getHora());
                monitor.setText(info.getMonitor());
                if (info.getNom().equals("Body pump")) {
                    Picasso.with(getContext())
                            .load(R.mipmap.bodypump)
                            .fit()
                            .into(fotsesio);
                } else if (info.getNom().equals("Step")) {
                    Picasso.with(getContext())
                            .load(R.mipmap.step)
                            .fit()
                            .into(fotsesio);
                } else if (info.getNom().equals("Body Combat")) {
                    Picasso.with(getContext())
                            .load(R.mipmap.bodycombat)
                            .fit()
                            .into(fotsesio);
                } else if (info.getNom().equals("Zumba")) {
                    Picasso.with(getContext())
                            .load(R.mipmap.zumba)
                            .fit()
                            .into(fotsesio);
                } else {
                    Picasso.with(getContext())
                            .load(R.mipmap.fitness)
                            .fit()
                            .into(fotsesio);
                }
            }
        };
        listSesiones.setAdapter(adapter);
    }

    /**
     * Metode per trobar les sesions amb un nom concret
     * @param nomSesio
     */
    private void configuracioLlistaMaquinesSesions(String nomSesio){
        Query queryRef = sesionRef.orderByChild("nom").equalTo(nomSesio);


        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Sesion a = snapshot.getValue(Sesion.class);
                items.add(a);
                System.out.println("" + items.size() + a.getNom());
                ArrayListAdapterSesions itemsAdapter = new ArrayListAdapterSesions(getContext(), R.layout.list_sesions_calendari, items);
                listSesiones.setAdapter(itemsAdapter);
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
     * Metode que retorna les dades extretes del DateDialog.
     * @param view Dialog Data picker
     * @param year Any sel·leccionat.
     * @param monthOfYear Mes del any sel·leccionat.
     * @param dayOfMonth Dia del mes sel·leccionat.
     */
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        databo=true;
        any = year;
        dia = dayOfMonth;
        mes = monthOfYear+1;
        dateText.setText(dia + "/" + mes + "/" + any);
        System.out.println(dateText.getText().toString());
        if(!sesio) {
            items.clear();
            consultarPerData(formatearData());
        }else{
            consultarPerDataSesio(formatearData());
        }
    }

    /**
     * Formateja la data al format adequat per fer la consulta
     * @return String amb data format 02/03/2016 (dd/MM/yyyy)
     */
    private String formatearData(){
        String time = "%02d/%02d/%04d";
        int[] times = {00, 00, 0000};
        times[0]=dia;
        times[1]=mes;
        times[2]=any;
        return time.format(time, times[0], times[1], times[2]);
    }

    /**
     * Metode per consultar sesions per dates
     * @param data data format (dd/MM/yyyy)
     */
    public void consultarPerData(String data){

        Query queryRef = sesionRef.orderByChild("data").equalTo(data);

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Sesion a = snapshot.getValue(Sesion.class);
                items.add(a);
                System.out.println("" + items.size() + a.getNom());
                ArrayListAdapterSesions itemsAdapter = new ArrayListAdapterSesions(getContext(), R.layout.list_sesions_calendari, items);
                listSesiones.setAdapter(itemsAdapter);
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
     * Metode per filtrar per dates la llista ja existent de sesions
     * @param data
     */
    public void consultarPerDataSesio(String data){
        ArrayList<Sesion>itemsPerDataSesio = new ArrayList<>();
        for(int i =0; i< items.size(); i++){
            if(items.get(i).getData().equals(data)){
                itemsPerDataSesio.add(items.get(i));
            }
        }
        ArrayListAdapterSesions itemsAdapter = new ArrayListAdapterSesions(getContext(), R.layout.list_sesions_calendari, itemsPerDataSesio);
        listSesiones.setAdapter(itemsAdapter);
    }

    /**
     * Metode per filtrar per dates la llista ja existent de sesions
     */
    public void consultarPerSesioData(){
        ArrayList<Sesion>itemsPerDataSesio = new ArrayList<>();
        for(int i =0; i< items.size(); i++){
            if(items.get(i).getNom().equals(sesioNom)){
                itemsPerDataSesio.add(items.get(i));
            }
        }
        ArrayListAdapterSesions itemsAdapter = new ArrayListAdapterSesions(getContext(), R.layout.list_sesions_calendari, itemsPerDataSesio);
        listSesiones.setAdapter(itemsAdapter);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
