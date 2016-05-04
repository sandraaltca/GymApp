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

import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Maquina;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.google.zxing.integration.android.IntentResult;



public class UtilizarMaquina extends Fragment {
    private Firebase maquinaRef;
    private Firebase ref;
    private ListView listStep;
    private ImageButton butonqrMaquines;



    public UtilizarMaquina() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /**
         * ocultem butó fab
         */

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
        listStep = (ListView)rootView.findViewById(R.id.nomLlistaDispo);
        butonqrMaquines =(ImageButton)rootView.findViewById(R.id.butonqrMaquines);

        configuracioButoQr();
        configuracioLlistaMaquines();
        return rootView;
    }
    private void configuracioLlistaMaquines(){

        final FirebaseListAdapter adapter = new FirebaseListAdapter<Maquina>(getActivity(), Maquina.class, R.layout.list_maquines, maquinaRef) {
            @Override
            protected void populateView(View v, Maquina info, int position) {
                TextView nom = (TextView) v.findViewById(R.id.nomMaquinaLlistaDispo);
                nom.setText(info.getNom());
            }
        };
        listStep.setAdapter(adapter);

        listStep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), StepMaquinaFragment.class);
                Maquina m= (Maquina) adapter.getItem(position);
                i.putExtra("item", m.getId());
                startActivity(i);
            }
        });

    }

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