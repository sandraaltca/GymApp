package com.example.sandra.gymapp.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Exercici;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sandra on 04/05/2016.
 */
public class ArrayListAdapterExerciciRutina extends ArrayAdapter<Exercici> {
    private TextView nom;
    private TextView temps;
    private ImageView fotoExercici;
    private Exercici exercici;

    public ArrayListAdapterExerciciRutina(Context context, int resource, ArrayList<Exercici> items) {
        super(context, resource, items);
        System.out.println("ADAPTER-------------------------------------------");
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_exercici, parent, false);
        }
        nom = (TextView) convertView.findViewById(R.id.nomExercici);
        temps = (TextView)convertView.findViewById(R.id.repeticionsEx);
        fotoExercici=(ImageView)convertView.findViewById(R.id.fotoExercici);
        exercici = getItem(position);

        configList();
        return convertView;
    }

    private void configList(){
        nom.setText(exercici.getNom());
        temps.setText(exercici.getRepeticions());

        Picasso.with(getContext())
                .load(exercici.getImage())
                .fit()
                .into(fotoExercici);
    }
}


