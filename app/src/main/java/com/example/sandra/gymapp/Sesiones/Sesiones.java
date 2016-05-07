package com.example.sandra.gymapp.Sesiones;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sandra.gymapp.ArrayAdapter.ViewPagerAdapter;
import com.example.sandra.gymapp.R;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sesiones extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    ViewPagerAdapter adapter;

    public Sesiones() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView =inflater.inflate(R.layout.fragment_sesiones, container, false);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabsSesion);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpagerSesion);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        return  rootView;
    }
    private void setupViewPager(ViewPager viewPager) {

        adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new SesionsEspecials(), "Especiales");
        adapter.addFragment(new SesionesOrdinarias(), "Ordinarias");
        viewPager.setAdapter(adapter);

    }

}
