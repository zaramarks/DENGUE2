package com.example.hppavilion.dengue.fragmentos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hppavilion.dengue.BancoDeDados;
import com.example.hppavilion.dengue.NavigationActivity;
import com.example.hppavilion.dengue.R;

/**
 * Created by zazah on 14/11/2016.
 */
public class addFocos extends Fragment   {
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


        endereço = (EditText)g.findViewById(R.id.ed);
        btn = (Button)g.findViewById(R.id.button2);

        return g;
    }

    public interface OnFragmentInteractionListener {
    }

    public void LocalizacaoAtual(){

        Toast.makeText(getActivity(), "oi", Toast.LENGTH_SHORT).show();
    }





}
