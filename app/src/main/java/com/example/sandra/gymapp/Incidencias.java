package com.example.sandra.gymapp;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.util.Linkify;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import com.google.zxing.integration.android.IntentResult;

import java.util.Date;



public class Incidencias extends Fragment {
    Firebase incidenciasRef;
    Firebase ref;
    EditText tipusdeIncidencia;
    EditText missatgeIncidencia;
    TextView tvResult;
    ImageButton buttonReader;

    public Incidencias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_incidencias, container, false);


        Firebase.setAndroidContext(getContext());
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        incidenciasRef = ref.child("Incidencias");

        missatgeIncidencia =(EditText)rootView.findViewById(R.id.missatge);
        tipusdeIncidencia = (EditText)rootView.findViewById(R.id.tipuIncidencia);
        buttonReader = (ImageButton) rootView.findViewById(R.id.butoQR);
        tvResult= (TextView) rootView.findViewById(R.id.tvResult);


        return rootView;
    }

    /**
     * Metode configuració botó Qr
     */
    private void configuracioButoQr() {
        buttonReader.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == 0) && (resultCode == -1)) {
            updateUITextViews(data.getStringExtra("SCAN_RESULT"), data.getStringExtra("SCAN_RESULT_FORMAT"));
        } else {

        }
    }

    private void handleResult(IntentResult scanResult) {
        if (scanResult != null) {
            updateUITextViews(scanResult.getContents(), scanResult.getFormatName());
        } else {
            Toast.makeText(getContext(), "No se ha leído nada :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUITextViews(String scan_result, String scan_result_format) {
        tvResult.setText(scan_result);
        Linkify.addLinks(tvResult, Linkify.ALL);
    }

    /**
     * Metode per pujar incidencies a la bbdd
     * @param incidencias Incidencia de la màquina.
     */
    public void pujarIncidencia(Incidencias incidencias) {

        Firebase nota = incidenciasRef.push();
        nota.setValue(incidencias);

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
