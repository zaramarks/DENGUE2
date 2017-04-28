package com.example.hppavilion.dengue.fragmentos;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.hppavilion.dengue.BancoDeDados;
import com.example.hppavilion.dengue.R;

/**
 * Created by HP PAVILION on 04/04/2016.
 */
public class Informacoes extends Fragment implements View.OnClickListener {
    public SearchView searchView;
    private TextView marcadores;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View f = inflater.inflate(R.layout.informacoes, container, false);
       // NavigationActivity.tb.setVisibility(View.INVISIBLE);

        marcadores = (TextView)f.findViewById(R.id.marcadores);

        BancoDeDados bd = new BancoDeDados(getActivity());
        SQLiteDatabase banco = bd.getReadableDatabase();


        return f;
    }

    @Override
    public void onClick(View v) {

    }



    public interface OnFragmentInteractionListener {
    }
}