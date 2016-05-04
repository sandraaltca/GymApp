package com.example.sandra.gymapp.Rutina;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.RutinaStandard;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;


public class RutinesStandard extends Fragment {
    Firebase ref;
    Firebase  infoGymRef;
    ListView rutinaStandar;

    public RutinesStandard() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_rutines_standard, container, false);
        Firebase.setAndroidContext(getContext());
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        infoGymRef = ref.child("RutinasStandard");
        rutinaStandar = (ListView) rootView.findViewById(R.id.rutinaStandar);
        configList();
        return rootView;
    }

    private void configList(){
        final FirebaseListAdapter adapter = new FirebaseListAdapter<RutinaStandard>(getActivity(), RutinaStandard.class, R.layout.list_exercici_rutina, infoGymRef) {
            @Override
            protected void populateView(View v, RutinaStandard info, int position) {

                TextView nom = (TextView)v.findViewById(R.id.nomRutina);
                TextView temps = (TextView)v.findViewById(R.id.tempsRutina);
                TextView nivell = (TextView)v.findViewById(R.id.nivelRutina);
                ImageView fotorutina = (ImageView)v.findViewById(R.id.imageView);
                nom.setText(info.getNom());
                temps.setText(info.getTemps()+" min");
                nivell.setText(info.getNivell());

                Picasso.with(getContext())
                        .load(info.getImage())
                        .fit()
                        .into(fotorutina);
            }
        };
        rutinaStandar.setAdapter(adapter);

        rutinaStandar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), DetailsRutina.class);
                i.putExtra("item", (RutinaStandard) adapter.getItem(position));
                startActivity(i);
            }
        });

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