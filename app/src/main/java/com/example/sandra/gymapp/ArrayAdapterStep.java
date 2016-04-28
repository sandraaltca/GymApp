package com.example.sandra.gymapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandra.gymapp.classesjava.Step;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 47419119l on 28/04/16.
 */

/***
 * ArrayAdapter per a steps a la funcionalitat " Utilitzat màquines"
 */
public class ArrayAdapterStep  extends ArrayAdapter<Step> {
    private TextView nStep;
    private TextView anotacio;
    private TextView descripcio;
    private ImageView foto;

    public ArrayAdapterStep (Context context, int resource, ArrayList<Step> items) {
        super(context, resource,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_maquines_util, parent, false);
        }
        /**
         * Creo el objecte step
         */

        Step step =getItem(position);
        /**
         * Instancies objectes
         */
        nStep = (TextView)convertView.findViewById(R.id.idStep);
        anotacio =(TextView)convertView.findViewById(R.id.anotacio);
        descripcio =(TextView)convertView.findViewById(R.id.descrip);
        foto =(ImageView)convertView.findViewById(R.id.foto);


        /*
        Introduim els valors
         */
        nStep.setText(String.valueOf(step.getnStep()));
        anotacio.setText(step.getAnotacio());
        descripcio.setText(step.getDescripcio());

        Picasso.with(getContext())
                .load(step.getFoto())
                .fit()
                .into(foto);


        return convertView;
    }
}