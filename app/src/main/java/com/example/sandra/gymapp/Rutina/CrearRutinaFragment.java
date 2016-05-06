package com.example.sandra.gymapp.Rutina;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.Sesiones.SesionesOrdinarias;
import com.example.sandra.gymapp.Sesiones.SesionsEspecials;

/**
 * A placeholder fragment containing a simple view.
 */
public class CrearRutinaFragment extends Fragment {
    private FragmentTabHost tabHost;

    public CrearRutinaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crear_rutina, container, false);
        tabHost= (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(), getFragmentManager(), android.R.id.tabcontent);

        //Creem les pestanyes
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Standard"), RutinesStandard.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Customize"), RutinesCustomizadas.class, null);

        return rootView;
    }
}
