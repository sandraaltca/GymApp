package com.example.sandra.gymapp.Rutina;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sandra.gymapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class test extends Fragment {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    public test() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test, container, false);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);


        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new RutinesStandard(), "Standard");
        adapter.addFragment(new RutinesCustomizadas(), "Custom");
        viewPager.setAdapter(adapter);
    }

}
