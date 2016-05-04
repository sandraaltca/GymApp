package com.example.sandra.gymapp.Sesiones;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.sandra.gymapp.R;


public class SesionesOrdinarias extends Fragment {


    public SesionesOrdinarias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_sesiones_ordinarias, container, false);
        WebView browser = (WebView) rootView.findViewById(R.id.webview);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.loadUrl("https://calendar.google.com/calendar/embed?src=iespoblenou.org_fed64opbufhfs8n831u60dj96k%40group.calendar.google.com&ctz=Europe/Madrid");

        return rootView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
