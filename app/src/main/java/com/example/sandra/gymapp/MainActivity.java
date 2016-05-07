package com.example.sandra.gymapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sandra.gymapp.Centro.ContactaCentro;
import com.example.sandra.gymapp.Centro.LocalizaCentro;
import com.example.sandra.gymapp.Maquines.Incidencias;
import com.example.sandra.gymapp.Maquines.UtilizarMaquina;
import com.example.sandra.gymapp.Rutina.CrearRutina;
import com.example.sandra.gymapp.Sesiones.Sesiones;
import com.example.sandra.gymapp.Sesiones.Sesions;
import com.example.sandra.gymapp.Usuari.Tuperfil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment;
    FragmentActivity frag;
    public static String uid;
    public static  FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Intent i = getIntent();
        uid =  i.getStringExtra("uid");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i ;
        boolean transaccion = false;
        boolean trans =false;

        if (id == R.id.tu_perfil) {
            i = new Intent(getBaseContext(),Tuperfil.class);
            i.putExtra("uid", uid);
            startActivity(i);
        } else if (id == R.id.tu_rutina) {
            fragment = new CrearRutina();
            transaccion = true;
        } else if (id == R.id.calendario_sesiones) {
            fragment = new Sesiones();
            transaccion = true;
        } else if (id == R.id.maquina_qr) {
            fragment = new UtilizarMaquina();
            transaccion = true;
        } else if (id == R.id.maquina_incidencia) {
            fragment = new Incidencias();
            transaccion = true;
        }else if (id == R.id.contacta_centro) {
            fragment = new ContactaCentro();
            transaccion = true;
        } else if (id == R.id.localiza_centro) {
            fragment = new LocalizaCentro();
            transaccion = true;
        }
        if(transaccion){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
