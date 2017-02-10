package com.example.hppavilion.dengue.fragmentos;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hppavilion.dengue.BancoDeDados;
import com.example.hppavilion.dengue.NavigationActivity;
import com.example.hppavilion.dengue.R;
import com.example.hppavilion.dengue.SQL;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by zazah on 14/11/2016.
 */
public class addFocos extends Fragment  {
    NavigationActivity nv = new NavigationActivity();
    public RadioGroup radioGroup;
    public RadioButton r1, r2, r3, r4, r5;
    public static EditText endereço;
   public  static String tipo= "tanto";
    public static String valeu;
   public Button btn;
    BancoDeDados helperr= new BancoDeDados(getActivity());
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View g = inflater.inflate(R.layout.addfocos, container, false);
        NavigationActivity.sv.setVisibility(View.INVISIBLE);

        FloatingActionButton FAB = (FloatingActionButton) g.findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LocalizacaoAtual();
            }
        });

       /* radioGroup = (RadioGroup)g.findViewById(R.id.RG);
        r1 = (RadioButton) g.findViewById(R.id.R1);
        r2 = (RadioButton) g.findViewById(R.id.R2);
        r3 = (RadioButton) g.findViewById(R.id.R3);
        r4 = (RadioButton) g.findViewById(R.id.R4);
        r5 = (RadioButton) g.findViewById(R.id.R5);*/
        endereço = (EditText)g.findViewById(R.id.ed);
        btn = (Button)g.findViewById(R.id.button2);
       /*r1.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     if (r1.isChecked()) {
                                         tipo="Dengue";
                                     }

                                 }
                             });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r2.isChecked()) {
                   tipo="Zika vírus";
                }

            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r3.isChecked()) {
                   tipo ="Chikungunya";
                }

            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r4.isChecked()) {
                    tipo="Nyongnyong";
                }

            }
        });
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r5.isChecked()) {
                    tipo="Guillaint barré";
                }

            }
        });*/

     /*   btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), tipo, Toast.LENGTH_SHORT).show();
               valeu = endereço.getText().toString().toLowerCase();
                Geocoder geocoder;
                List<Address> addresses = null;
                geocoder = new Geocoder(getActivity(), Locale.getDefault());
                List<Address> list = null;
                try {
                    list = geocoder.getFromLocationName(valeu, 1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Address add = list.get(0);

                final double lattt = add.getLatitude();

                final double lnggg = add.getLongitude();
                String te = "oii";
                Log.v("latitude", String.valueOf(lattt));
                SQL ah = new SQL();
                ah.setPnome(te);
                ah.setPdoenca(tipo);
                ah.setPendereco(valeu);
                ah.setPlat(lattt);
                ah.setPlng(lnggg);
                helperr.insertContactt(ah);
                Toast.makeText(getActivity(), "Foco registrado!", Toast.LENGTH_SHORT).show();
            }
            });

               /* Geocoder geocoder;
                List<Address> addresses = null;
                geocoder = new Geocoder(getActivity(), Locale.getDefault());
                List<Address> list = null;
                try {
                    list = geocoder.getFromLocationName(valeu, 1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Address add = list.get(0);

                final double lattt = add.getLatitude();

                final double lnggg = add.getLongitude();



                LatLng pos= new LatLng(lattt, lnggg);


                    MarkerOptions options = new MarkerOptions()
                            .title(" Foco - Dengue" )
                            .snippet(String.valueOf(endereço))
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_RED))

                            .position(pos);

                    NavigationActivity.mMap.addMarker(options);
                NavigationActivity.drawCircle(pos);
               Log.v("tentativa", String.valueOf(endereço));
            }*/

        return g;
    }

    public interface OnFragmentInteractionListener {
    }

    public void LocalizacaoAtual(){
        Toast.makeText(getActivity(), "oi", Toast.LENGTH_SHORT).show();
    }





}
