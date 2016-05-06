package com.example.sandra.gymapp.Rutina;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arasthel.asyncjob.AsyncJob;
import com.example.sandra.gymapp.ArrayAdapter.ArrayListAdapterRutines;
import com.example.sandra.gymapp.FireBase.FireBaseConfiguracio;
import com.example.sandra.gymapp.MainActivity;
import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.RutinaCustomize;
import com.example.sandra.gymapp.classesjava.RutinaStandard;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;

public class RutinesCustomizadas extends Fragment {
    private ListView rutinesCustom;
    private ArrayList<RutinaCustomize> rutines;
    private String uidUser;
    private ArrayListAdapterRutines itemsAdapter;
    private Firebase refRutinesPersonalitzades;
    public RutinesCustomizadas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rutines_customizadas, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),FormulariRutinesCustom.class);
                startActivity(intent);
            }
        });
        rutines = new ArrayList<>();
        FireBaseConfiguracio fireBaseConfiguracio = new FireBaseConfiguracio();
        fireBaseConfiguracio.configFirebase(getContext());
        refRutinesPersonalitzades = fireBaseConfiguracio.getRutinesCustomizades();

        rutinesCustom= (ListView)rootView.findViewById(R.id.rutinesCustom);
        uidUser = MainActivity.uid;
        configurarLlista();
        return rootView;
    }

    private void configurarLlista(){

        Query queryRef = refRutinesPersonalitzades.orderByChild("uidUser").equalTo(uidUser);
        itemsAdapter = new ArrayListAdapterRutines(queryRef, getActivity(), R.layout.list_exercici_rutina,getContext());
        rutinesCustom.setAdapter(itemsAdapter);

        rutinesCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), DetailsRutina.class);
                i.putExtra("id", "custom");
                i.putExtra("item",itemsAdapter.getItem(position));
                startActivity(i);
            }
        });
    }

}
