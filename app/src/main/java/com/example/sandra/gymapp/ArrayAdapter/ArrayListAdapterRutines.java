package com.example.sandra.gymapp.ArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Chat;
import com.example.sandra.gymapp.classesjava.RutinaCustomize;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sandra on 05/05/2016.
 */
public class ArrayListAdapterRutines extends FirebaseListAdapter<RutinaCustomize> {
    private Context context;

    public ArrayListAdapterRutines(Query ref, Activity activity, int layout, Context context) {
        super(activity, RutinaCustomize.class, layout, ref);
        this.context=context;
    }

    @Override
    protected void populateView(View convertView,RutinaCustomize info) {


        TextView nom = (TextView)convertView.findViewById(R.id.nomRutina);
        TextView temps = (TextView)convertView.findViewById(R.id.tempsRutina);
        TextView nivell = (TextView)convertView.findViewById(R.id.nivelRutina);
        ImageView fotorutina = (ImageView)convertView.findViewById(R.id.imageView);



        nom.setText(info.getNom());
        temps.setText(info.getTemps()+" min");
        nivell.setText(info.getNivell());

        Picasso.with(context)
                .load(info.getImage())
                .fit()
                .into(fotorutina);



    }


}