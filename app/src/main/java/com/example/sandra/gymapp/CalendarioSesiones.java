package com.example.sandra.gymapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class CalendarioSesiones extends Fragment {

    private FragmentTabHost tabHost;

    public CalendarioSesiones() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView =inflater.inflate(R.layout.fragment_calendario_sesiones, container, false);
        tabHost= (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(), getFragmentManager(), android.R.id.tabcontent);

        //Creem les pestanyes
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Sesiones especiales"), SesionsEspecials.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Sesiones ordinarias"), SesionesOrdinarias.class, null);

        return rootView;
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
