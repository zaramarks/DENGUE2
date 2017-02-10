package com.example.hppavilion.dengue.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.hppavilion.dengue.NavigationActivity;
import com.example.hppavilion.dengue.R;
import com.example.hppavilion.dengue.resultadoBusca;

/**
 * Created by zazah on 08/08/2016.
 */
public class buscar extends Fragment implements View.OnClickListener {
    private CheckBox checkBox, checkBox2, checkBox3;
    public static EditText  etENDERECO, etNOME;
    public static Spinner  etDOENCA ;
    public static int a=0, b=0, c=0;

    private Switch nome, doenca, endereco;

    public static ArrayAdapter<String> Opcoes;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View g = inflater.inflate(R.layout.buscar_fragment, container, false);
        NavigationActivity.sv.setVisibility(View.INVISIBLE);

        checkBox = (CheckBox) g.findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) g.findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) g.findViewById(R.id.checkBox3);


        etNOME = (EditText)g.findViewById(R.id.etNome);
        etDOENCA = (Spinner) g.findViewById(R.id.etDoenca);
        etENDERECO = (EditText)g.findViewById(R.id.etEndereco);

        etNOME.setVisibility(View.INVISIBLE);
        etDOENCA.setVisibility(View.INVISIBLE);
        etENDERECO.setVisibility(View.INVISIBLE);


        Opcoes = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        Opcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etDOENCA.setAdapter(Opcoes);


        Opcoes.add("Dengue");
        Opcoes.add("Zika vírus");
        Opcoes.add("Chikungunya");
        Opcoes.add("Nyongnyong");
        Opcoes.add("Guillaint barré");
     /*   nome = (Switch)g.findViewById(R.id.switch1);

        nome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etNOME.setVisibility(View.VISIBLE);
                } else {
                    etNOME.setVisibility(View.INVISIBLE);
                }
            }
        });*/

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    etNOME.setVisibility(View.VISIBLE);
                    a=1;
                }else{
                    etNOME.setVisibility(View.INVISIBLE);
                    a=0;
                }

            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()){
                    etDOENCA.setVisibility(View.VISIBLE);
                    b=1;
                }else{
                    etDOENCA.setVisibility(View.INVISIBLE);
                    b=0;
                }

            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox3.isChecked()) {
                    etENDERECO.setVisibility(View.VISIBLE);
                    c=1;
                } else {
                    etENDERECO.setVisibility(View.INVISIBLE);
                    c=0;
                }
            }
            });

        Button BUSCAR = (Button) g.findViewById(R.id.buscar);
        BUSCAR.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), resultadoBusca.class);
                startActivity(intent);

            }
        });
        return g;
    }



    @Override
    public void onClick(View v) {

    }

    public interface OnFragmentInteractionListener {
    }
    }


