package com.example.sandra.gymapp.Maquines;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandra.gymapp.FireBase.FireBaseConfiguracio;
import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Maquina;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.google.zxing.integration.android.IntentResult;



public class UtilizarMaquina extends Fragment {
    private ImageButton butonqrMaquines;



    public UtilizarMaquina() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView =inflater.inflate(R.layout.fragment_utilizar_maquina, container, false);
        /**
         * Instanciem objectes layout.
         */
        butonqrMaquines =(ImageButton)rootView.findViewById(R.id.butonqrMaquines);

        configuracioButoQr();
        return rootView;
    }

    /**
     * Metode per configurar el botó Qr.
     */
    private void configuracioButoQr() {

        butonqrMaquines.setOnClickListener(new View.OnClickListener() {
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

    /**
     * Metode que s'executara al sortir el resultat de l bacecore reader.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == 0) && (resultCode == -1)) {
          //  updateUITextViews(data.getStringExtra("SCAN_RESULT"), data.getStringExtra("SCAN_RESULT_FORMAT"));

            Intent i = new Intent(getContext(),StepMaquina.class);
            i.putExtra("id",data.getStringExtra("SCAN_RESULT"));
            startActivity(i);
        } else {

        }
    }

    private void handleResult(IntentResult scanResult) {
        if (scanResult != null) {
           // updateUITextViews(scanResult.getContents(), scanResult.getFormatName());
            Intent i = new Intent(getContext(),StepMaquina.class);
            i.putExtra("id",scanResult.getContents());
            startActivity(i);
        } else {
            Toast.makeText(getContext(), "No s'ha pogut lleguir cap codi Qr", Toast.LENGTH_SHORT).show();
        }
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