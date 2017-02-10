package com.example.hppavilion.dengue.fragmentos;

import android.Manifest;

import android.app.Activity;

import android.support.v4.app.ActivityCompat;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.support.v4.app.Fragment;

import com.example.hppavilion.dengue.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends Fragment implements OnMapReadyCallback {
    private SupportMapFragment mapFrag;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmento1, container, false);

        GoogleMapOptions options = new GoogleMapOptions();
        options.zOrderOnTop(true);



        //mapFrag = (SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);
        //map = mapFrag.getMap();
        //configMap();

       /* GoogleMapOptions options = new GoogleMapOptions();
        options.zOrderOnTop(true);
        SupportMapFragment mapFrag = SupportMapFragment.newInstance(options);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.Container1, mapFrag);
        ft.commit();*/

        return rootView;
    }




    public void onMapReady(GoogleMap googleMap) {

    }




    public interface OnFragmentInteractionListener {
    }


    @Override
    public void onResume(){
        super.onResume();

		/*new Thread(){
			public void run(){
				while(mapFrag.getMap() == null){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				runOnUiThread(new Runnable(){
					public void run(){
						configMap();
					}
				});
			}
		}.start();*/
    }



    public interface NavigationDrawerCallBack {
    }


}
