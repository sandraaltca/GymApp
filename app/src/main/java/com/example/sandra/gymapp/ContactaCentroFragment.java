package com.example.sandra.gymapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactaCentroFragment extends Fragment {
    TextView emisor ;
    TextView assumpte ;
    TextView missatge ;
    Button enviar;
    public ContactaCentroFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_contacta_centro, container, false);

        emisor = (TextView)rootView.findViewById(R.id.correoUsuarioEnvi);
        assumpte = (TextView)rootView.findViewById(R.id.asumptConta);
        missatge = (TextView)rootView.findViewById(R.id.misstatge);
        enviar = (Button)rootView.findViewById(R.id.Enviar);

        /**
         * Metode quan apretem al buto de enviar.
         */
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creem intent per a la actividad d'enviar
                Intent itSend = new Intent(android.content.Intent.ACTION_SEND);
                itSend.setType("plain/text");

                //Introduïm el els valors que cadascun dels camps

                itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ "sandraaltafaja@gmail.com"});
                itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, assumpte.getText().toString());
                itSend.putExtra(android.content.Intent.EXTRA_TEXT, missatge.getText().toString());
                //iniciem la actividad
                startActivity(itSend);
            }
        });
        return rootView;
    }
}
