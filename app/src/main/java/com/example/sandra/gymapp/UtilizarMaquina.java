package com.example.sandra.gymapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandra.gymapp.classesjava.InfoGym;
import com.example.sandra.gymapp.classesjava.Maquina;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class UtilizarMaquina extends Fragment {
    private Firebase maquinaRef;
    private Firebase ref;
    private ListView listStep;
    private String idmaquina;
    private ImageButton butonqrMaquines;

    private ArrayAdapterStep adapter;

    public UtilizarMaquina() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_utilizar_maquina, container, false);
        /**
         * Creo referencia firebase
         */
        Firebase.setAndroidContext(getContext());
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        maquinaRef = ref.child("Maquines");
        /**
         * Instanciem objectes layout.
         */
        listStep = (ListView)rootView.findViewById(R.id.nomMaquinaLlistaDispo);
        butonqrMaquines =(ImageButton)rootView.findViewById(R.id.butonqrMaquines);

        configuracioButoQr();
        configuracioLlistaMaquines();
        return rootView;
    }
    private void configuracioLlistaMaquines(){
        FirebaseListAdapter adapter = new FirebaseListAdapter<Maquina>(getActivity(), Maquina.class, R.layout.list_maquines, maquinaRef) {
            @Override
            protected void populateView(View v, Maquina info, int position) {
                TextView nom = (TextView) v.findViewById(R.id.nomMaquinaLlistaDispo);
                nom.setText(info.getNom());
            }
        };
        listStep.setAdapter(adapter);
    }

    private void configuracioButoQr() {
/*
        //MainActivity.fab.setImageResource(R.id.butoQR);
        MainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //new IntentIntegrator(QrReaderActity.this).initiateScan();
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intent, 0);
                } catch (ActivityNotFoundException exception) {

                }
            }
        });
*/

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == 0) && (resultCode == -1)) {
          //  updateUITextViews(data.getStringExtra("SCAN_RESULT"), data.getStringExtra("SCAN_RESULT_FORMAT"));
        } else {

        }
    }

    private void handleResult(IntentResult scanResult) {
        if (scanResult != null) {
           // updateUITextViews(scanResult.getContents(), scanResult.getFormatName());
        } else {
            Toast.makeText(getContext(), "No s'ha pogut lleguir cap codi Qr", Toast.LENGTH_SHORT).show();
        }
    }
/*
    private void updateUITextViews(String scan_result, String scan_result_format) {

        idmaquina = scan_result;
        Query queryRef = maquinaRef.orderByChild("id").equalTo(idmaquina);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Maquina a=  snapshot.getValue(Maquina.class);
                ArrayList steps = a.getSteps();

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

*/


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