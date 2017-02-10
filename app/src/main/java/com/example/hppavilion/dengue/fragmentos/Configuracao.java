package com.example.hppavilion.dengue.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.example.hppavilion.dengue.NavigationActivity;
import com.example.hppavilion.dengue.R;

/**
 * Created by zazah on 24/06/2016.
 */
public class Configuracao extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_configuracao, container, false);
        NavigationActivity.sv.setVisibility(View.INVISIBLE);
        return v;
    }

    public interface OnFragmentInteractionListener {
    }
}
