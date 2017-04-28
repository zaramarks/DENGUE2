package com.example.hppavilion.dengue;

/**
 * Created by zazah on 13/08/2016.
 */

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hppavilion.dengue.fragmentos.buscar;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static com.example.hppavilion.dengue.R.id.map;


public class resultadoComMapa extends AppCompatActivity{

    private LatLng location;

    public static GoogleMap mMap;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_mapa);
        toolbar = (Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Map");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(map)).getMap();

        Geocoder gcc = new Geocoder(this);
        List<Address> list = null;
        try {
            list = gcc.getFromLocationName(buscar.etendereco, 1);
        } catch (IOException e) {
            Toast.makeText(this, " Could not get address. Connect to the internet, and try again!", Toast.LENGTH_SHORT).show();
        }


        Address add = list.get(0);
        String locality = add.getLocality();


        final double latt = add.getLatitude();

        final double lngg = add.getLongitude();

        location = new LatLng(latt, lngg);

        final CameraPosition cp = new CameraPosition.Builder().target(location).zoom(13).bearing(0).tilt(00).build();
        CameraUpdate cam = CameraUpdateFactory.newCameraPosition(cp);
        mMap.moveCamera(cam);

        for (int i = 0; i < buscar.result.size(); i++) {
            String  doencaP =  buscar.result.get(i).getTipo();
            String  nomeP =  buscar.result.get(i).getNome();
            String  enderecoP = buscar.result.get(i).getEndereco();

            Double lat =  buscar.result.get(i).getLat();
            Double lng =  buscar.result.get(i).getLng();

            LatLng pos = new LatLng(lat, lng);

            switch (doencaP) {
                case "Dengue":
                    MarkerOptions options = new MarkerOptions()
                            .title(nomeP + " - Dengue")
                            .snippet(enderecoP)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_RED))

                            .position(pos);

                    mMap.addMarker(options);
                    break;
                case "Zika vírus":
                    MarkerOptions options1 = new MarkerOptions()
                            .title(nomeP + " - Zika vírus")
                            .snippet(enderecoP)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_GREEN))

                            .position(pos);

                    mMap.addMarker(options1);
                    break;
                case "Chikungunya":
                    MarkerOptions options2 = new MarkerOptions()
                            .title(nomeP + " - Chicungunya")
                            .snippet(enderecoP)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_AZURE))
                            .position(pos);

                    mMap.addMarker(options2);
                    break;
                case "Nyongnyong":
                    MarkerOptions options3 = new MarkerOptions()
                            .title(nomeP + " - Nyongnyong")
                            .snippet(enderecoP)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_ORANGE))
                            .position(pos);

                    mMap.addMarker(options3);
                    break;
                case "Guillain barré":
                    MarkerOptions options4 = new MarkerOptions()
                            .title(nomeP + " - Guillain barré")
                            .snippet(enderecoP)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_YELLOW))
                            .position(pos);

                    mMap.addMarker(options4);
                    break;
                case "Foco":

                    drawCircle(pos);
                    break;

            }
            }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), resultadoBusca.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    public static void drawCircle(LatLng point) {

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle
        circleOptions.radius(500);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(0x550000);

        // Border width of the circle
        circleOptions.strokeWidth(2);

        // Adding the circle to the GoogleMap
        mMap.addCircle(circleOptions);

    }
}