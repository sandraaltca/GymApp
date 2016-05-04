package com.example.sandra.gymapp.Maquines;


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

import com.example.sandra.gymapp.MainActivity;
import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Incidencia;
import com.firebase.client.Firebase;

import com.google.zxing.integration.android.IntentResult;

import java.util.Date;



public class Incidencias extends Fragment {
    private Firebase incidenciasRef;
    private Firebase ref;
    private EditText tipusdeIncidencia;
    private EditText missatgeIncidencia;
    private TextView tvResult;
    private ImageButton buttonReader;
    private ImageButton enviar;
    private String uid;
    public Incidencias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_incidencias, container, false);
       uid = MainActivity.uid;
        /**
         * Creem una referencia a firebase.
         */
        Firebase.setAndroidContext(getContext());
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        incidenciasRef = ref.child("Incidencias");
        /**
         * Instanciem els objectes
         */
        missatgeIncidencia =(EditText)rootView.findViewById(R.id.missatge);
        tipusdeIncidencia = (EditText)rootView.findViewById(R.id.textView17);
        buttonReader = (ImageButton) rootView.findViewById(R.id.butoQR);
        tvResult= (TextView) rootView.findViewById(R.id.tvResult);
        enviar = (ImageButton) rootView.findViewById(R.id.enviarIncidencia);

        /**
         * Configurem el botons
         */
        configuracioButoQr();
        configuracioButoEnviar();



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
    private String extreureDataActual(){
        Date fecha = new Date();
        System.out.println(fecha);
        return fecha.toString();
    }
    /**
     * Metode per configurar el boto de enviar
     */
    private void configuracioButoEnviar(){

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Incidencia incidencia = new Incidencia();
                incidencia.setIdMaquina(tvResult.getText().toString());
                incidencia.setRevisat(false);
                incidencia.setUser(uid);
                incidencia.setIncidencia(missatgeIncidencia.getText().toString());
                incidencia.setTipusIncidencia(tipusdeIncidencia.getText().toString());
                incidencia.setData(extreureDataActual());


                pujarIncidencia(incidencia);
                missatgeIncidencia.setText("");
                tipusdeIncidencia.setText("");

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
            Toast.makeText(getContext(), "No s'ha pogut lleguir cap codi Qr", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUITextViews(String scan_result, String scan_result_format) {
        tvResult.setText(scan_result);
        Linkify.addLinks(tvResult, Linkify.ALL);
    }

    /**
     * Metode per pujar incidencies a la bbdd
     * @param incidencia Incidencia de la màquina.
     */
    public void pujarIncidencia(Incidencia incidencia) {

        Firebase nota = incidenciasRef.push();
        nota.setValue(incidencia);

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
