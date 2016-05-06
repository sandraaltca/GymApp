package com.example.sandra.gymapp.Maquines;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sandra.gymapp.ArrayAdapter.ArrayAdapterStep;
import com.example.sandra.gymapp.FireBase.FireBaseConfiguracio;
import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Maquina;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class StepMaquinaFragment extends Fragment {
    private Firebase ref;
    private Firebase maquinaRef;
    private ArrayAdapterStep adapter;
    private ListView listStep;
    private String idmaquina;
    public StepMaquinaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_step_maquina, container, false);

        FireBaseConfiguracio fireBaseConfiguracio = new FireBaseConfiguracio();
        fireBaseConfiguracio.configFirebase(getContext());
        maquinaRef   =fireBaseConfiguracio.getMaquines();


        Intent i = getActivity().getIntent();
        idmaquina =i.getStringExtra("id");
        listStep = (ListView) rootView.findViewById(R.id.listStep);
        configList();
        return rootView;
    }

    private void configList(){
        Query queryRef = maquinaRef.orderByChild("id").equalTo(idmaquina);

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Maquina a=  snapshot.getValue(Maquina.class);
                ArrayList steps = a.getSteps();
                getActivity().setTitle(a.getNom());

                adapter = new ArrayAdapterStep(
                        getContext(),
                        R.layout.list_maquines_util,
                        steps
                );
                listStep.setAdapter(adapter);

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
