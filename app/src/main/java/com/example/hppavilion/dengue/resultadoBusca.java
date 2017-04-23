package com.example.hppavilion.dengue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by zazah on 08/08/2016.
 */
public class resultadoBusca extends AppCompatActivity {
    private Toolbar toolbar;
    public static String d, e, n;
    private ArrayList<String> Anome = new ArrayList<>();
    private ArrayList<String> Aendereco = new ArrayList<>();
    private ArrayList<Integer> Adoenca = new ArrayList<>();
    private ListView lv;
    public int a = 0;
    private String nomee, doencaa, enderecoo;
    private Double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reultado_busca);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resultado da Busca");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public interface OnFragmentInteractionListener {
    }
}
