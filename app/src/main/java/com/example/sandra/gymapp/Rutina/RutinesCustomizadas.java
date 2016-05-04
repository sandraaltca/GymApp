package com.example.sandra.gymapp.Rutina;



import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sandra.gymapp.MainActivity;
import com.example.sandra.gymapp.R;

public class RutinesCustomizadas extends Fragment {
    private ListView rutinesCustom;
    private String idUser;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        rutinesCustom= (ListView)rootView.findViewById(R.id.rutinesCustom);
        idUser = MainActivity.uid;

        return rootView;
    }



}
