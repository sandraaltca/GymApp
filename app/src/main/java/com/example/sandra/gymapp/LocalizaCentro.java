package com.example.sandra.gymapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import com.example.sandra.gymapp.classesjava.InfoGym;
import com.firebase.ui.FirebaseListAdapter;


public class LocalizaCentro extends Fragment {
    private Firebase infoGymRef;
    private Firebase ref;
    private ListView listCentre;

    public LocalizaCentro() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_localiza_centro, container, false);

        /**
         * Contecem amb firebase.
         */
        Firebase.setAndroidContext(getContext());
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        infoGymRef = ref.child("InfoGym");
        /**
         * Objectes layout.
         */

        listCentre = (ListView)rootView.findViewById(R.id.listCentre);

        /**
         * Configuració
         */

        configLlistatGimnas();
        return rootView;
    }
    /**
     * Metode per configurar el llistat de gimnasos.
     */
    private void configLlistatGimnas(){

        FirebaseListAdapter adapter = new FirebaseListAdapter<InfoGym>(getActivity(), InfoGym.class, R.layout.list_localiza_centro, infoGymRef) {
            @Override
            protected void populateView(View v, InfoGym info, int position) {
                TextView nom = (TextView) v.findViewById(R.id.nomCentre);
                TextView direccio = (TextView) v.findViewById(R.id.ubicacioCentre);
                TextView email = (TextView) v.findViewById(R.id.emailCentre);
                TextView telefon = (TextView) v.findViewById(R.id.telfCentre);
                TextView semana = (TextView) v.findViewById(R.id.horariCentre);
                TextView horari2 = (TextView)v.findViewById(R.id.horari2);
                TextView horari3 = (TextView)v.findViewById(R.id.horari3);


                nom.setText(info.getNombreGym());
                direccio.setText(info.getDireccionGym());
                email.setText(info.getCorreoElectronicoGym());
                telefon.setText(String.valueOf(info.getTelefonoGym()));
                semana.setText("Lunes- viernes : " + info.getHorarioGym()[0]);
                horari2.setText("Sabados : " + info.getHorarioGym()[1]);
                horari3.setText("Domingos y festivos : " + info.getHorarioGym()[2]);

            }
        };
        listCentre.setAdapter(adapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
