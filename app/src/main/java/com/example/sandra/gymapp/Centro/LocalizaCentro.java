package com.example.sandra.gymapp.Centro;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.InfoGym;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.FirebaseListAdapter;


public class LocalizaCentro extends Fragment {
    private Firebase infoGymRef;
    private Firebase ref;
    private ListView listCentre;
    private MapView map;
    private IMapController mapController;
    private MyLocationNewOverlay myLocationNewOverlay;
    private RadiusMarkerClusterer radiusMarkerClusterer;

    public LocalizaCentro() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_localiza_centro, container, false);

        /**
         * Contecem amb firebase.
         */
        Firebase.setAndroidContext(getContext());
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        infoGymRef = ref.child("InfoGym");
        /**
         * Objectes layout.
         */

        listCentre = (ListView)rootView.findViewById(R.id.listCentre);
        map = (MapView) rootView.findViewById(R.id.map);

        /**
         * Configuració
         */

        configLlistatGimnas();
        configuraciodelMapa();
        posicionsGimnas();
        return rootView;
    }
    /**
     * Metode per configurar el llistat de gimnasos.
     */
    private void configLlistatGimnas(){

        FirebaseListAdapter adapter = new FirebaseListAdapter<InfoGym>(getActivity(), InfoGym.class, R.layout.list_localiza_centro, infoGymRef) {
            @Override
            protected void populateView(View v, InfoGym info, int position) {
                TextView nom = (TextView) v.findViewById(R.id.nomCentre);
                TextView direccio = (TextView) v.findViewById(R.id.ubicacioCentre);
                TextView email = (TextView) v.findViewById(R.id.emailCentre);
                TextView telefon = (TextView) v.findViewById(R.id.telfCentre);
                TextView semana = (TextView) v.findViewById(R.id.horariCentre);

                nom.setText(info.getNombreGym());
                direccio.setText(info.getDireccionGym());
                email.setText(info.getCorreoElectronicoGym());
                telefon.setText(String.valueOf(info.getTelefonoGym()));
                semana.setText("Lunes- viernes : " + info.getHorarioGym()[0]);


            }
        };
        listCentre.setAdapter(adapter);
    }
    /**
     * Metode que extreu del firebase totes les posicions dels bolets i les col·loca en el mapa
     */

    private void posicionsGimnas()
    {
        radiusMarkerClusterer = new RadiusMarkerClusterer(getContext());
        map.getOverlays().add(radiusMarkerClusterer);
        radiusMarkerClusterer.setRadius(100);


        infoGymRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    InfoGym ubicacio = postSnapshot.getValue(InfoGym.class);
                    Marker gimnasPoint = new Marker(map);
                    GeoPoint point = new GeoPoint(ubicacio.getLatitudGym(), ubicacio.getLongitudGym());
                    //Afegueixo icono personalitzada als "marker" del mapa.
                    gimnasPoint.setIcon(getResources().getDrawable(R.drawable.ic_room_indigo_a400_24dp));
                    gimnasPoint.setPosition(point);
                    gimnasPoint.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    //Afegueixo titol
                    gimnasPoint.setTitle(ubicacio.getNombreGym());
                    //Afegueixo un "retall"
                    gimnasPoint.setSnippet(ubicacio.getDireccionGym());
                    //Afegueixo sub-descripcio
                    gimnasPoint.setSubDescription(String.valueOf(ubicacio.getTelefonoGym()));
                    gimnasPoint.setAlpha(0.6f);
                    radiusMarkerClusterer.add(gimnasPoint);
                }
                radiusMarkerClusterer.invalidate();
                map.invalidate();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    /**
     * Configuració inicial del mapa.
     */
    private void configuraciodelMapa()
    {
        map.setTileSource(TileSourceFactory.MAPQUESTOSM);
        map.setTilesScaledToDpi(true);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(15);

        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        myLocationNewOverlay = new MyLocationNewOverlay(getContext(), new GpsMyLocationProvider(getContext()), map);
        myLocationNewOverlay.enableMyLocation();

        myLocationNewOverlay.runOnFirstFix(new Runnable() {
            public void run() {
                mapController.animateTo(myLocationNewOverlay
                        .getMyLocation());
            }
        });

        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(map);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(displayMetrics.widthPixels / 2, 10);
        CompassOverlay compassOverlay = new CompassOverlay(getContext(), new InternalCompassOrientationProvider(getContext()), map);
        compassOverlay.enableCompass();
        map.getOverlays().add(myLocationNewOverlay);
        map.getOverlays().add(scaleBarOverlay);
        map.getOverlays().add(compassOverlay);


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
