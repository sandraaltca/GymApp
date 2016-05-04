package com.example.sandra.gymapp.ArrayAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Sesion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArrayListAdapterSesions extends ArrayAdapter<Sesion> {
    private TextView nom ;
    private TextView data ;
    private TextView monitor;
    private ImageView fotsesio;
    public ArrayListAdapterSesions (Context context, int resource, ArrayList<Sesion> items) {
        super(context, resource, items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_sesions_calendari, parent, false);
        }
        nom = (TextView) convertView.findViewById(R.id.nomSesio);
        data = (TextView) convertView.findViewById(R.id.dataHorariSesio);
        monitor = (TextView) convertView.findViewById(R.id.NomMonitora);
        fotsesio = (ImageView) convertView.findViewById(R.id.fotoSesio);

        Sesion info = getItem(position);

        nom.setText(info.getNom());
        data.setText(info.getData() + " " + info.getHora());
        monitor.setText(info.getMonitor());
        if (info.getNom().equals("Body pump")) {
            Picasso.with(getContext())
                    .load(R.mipmap.bodypump)
                    .fit()
                    .into(fotsesio);
        } else if (info.getNom().equals("Step")) {
            Picasso.with(getContext())
                    .load(R.mipmap.step)
                    .fit()
                    .into(fotsesio);
        } else if (info.getNom().equals("Body Combat")) {
            Picasso.with(getContext())
                    .load(R.mipmap.bodycombat)
                    .fit()
                    .into(fotsesio);
        } else if (info.getNom().equals("Zumba")) {
            Picasso.with(getContext())
                    .load(R.mipmap.zumba)
                    .fit()
                    .into(fotsesio);
        } else {
            Picasso.with(getContext())
                    .load(R.mipmap.fitness)
                    .fit()
                    .into(fotsesio);
        }

        return  convertView;
    }
}