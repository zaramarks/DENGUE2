package com.example.hppavilion.dengue.fragmentos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hppavilion.dengue.BancoDeDados;
import com.example.hppavilion.dengue.NavigationActivity;
import com.example.hppavilion.dengue.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by zazah on 14/11/2016.
 */
public class addFocos extends Fragment implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    NavigationActivity nv = new NavigationActivity();
    public RadioGroup radioGroup;
    public RadioButton r1, r2, r3, r4, r5;
    public static EditText endereço;
    public static String tipo = "tanto";
    public static String valeu;
    public Button btn;
    private GoogleApiClient googleApiClient;
    BancoDeDados helperr = new BancoDeDados(getActivity());

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


        endereço = (EditText) g.findViewById(R.id.ed);
        btn = (Button) g.findViewById(R.id.button2);

        return g;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public interface OnFragmentInteractionListener {
    }

    public void LocalizacaoAtual() {

    }

}
