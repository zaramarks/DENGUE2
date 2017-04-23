package com.example.hppavilion.dengue.fragmentos;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hppavilion.dengue.BancoDeDados;
import com.example.hppavilion.dengue.MenuDeEntrada;
import com.example.hppavilion.dengue.NavigationActivity;
import com.example.hppavilion.dengue.R;
import com.example.hppavilion.dengue.adapter.PlaceAutocompleteAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by HP PAVILION on 08/04/2016.
 */
public class AddCasos extends Fragment  {

    public static EditText Doenca, Estado, Cidade, Bairro, Rua, Nome;
    public static Spinner SpnOpcoes;
    private Button RegistrarCaso;
    private PlaceAutocompleteAdapter mAdapter;
    public static AutoCompleteTextView Endereço;
    public static ArrayAdapter<String> adpOpcoes;
    public static String PEndereço;
    public static Button BViewMap, BRegistrar;
    public static Toolbar myToolbar;
    public SearchView sv;
    public TextView nome, doenca, blas, endereco;
    public EditText nomeE, doencaE;

    public AutoCompleteTextView enderecoE;

    BancoDeDados helper2= new BancoDeDados(getActivity());

    public static final LatLngBounds BOUNS_CAMPO_GRANDE = new LatLngBounds(new LatLng(-20.595001, -54.503947), new LatLng(-20.364035, -54.765508));


    View.OnClickListener mAutocompleteClickListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addcasos, container, false);

//        getActivity().getActionBar().setTitle("Whatever you want");
        //NavigationActivity.tb.setVisibility(View.INVISIBLE);
        NavigationActivity.sv.setVisibility(View.INVISIBLE);
        //Doenca = (EditText) v.findViewById(R.id.DoencaP);
        Nome = (EditText) v.findViewById(R.id.NomeP);
        BRegistrar = (Button) v.findViewById(R.id.Registrar);
        SpnOpcoes = (Spinner) v.findViewById(R.id.spinner);

        RegistrarCaso = (Button) v.findViewById(R.id.Registrar);
        adpOpcoes = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        adpOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnOpcoes.setAdapter(adpOpcoes);


        adpOpcoes.add("Dengue");
        adpOpcoes.add("Zika vírus");
        adpOpcoes.add("Chikungunya");
        adpOpcoes.add("Nyongnyong");
        adpOpcoes.add("Guillain barré");

        NavigationActivity act = (NavigationActivity) getActivity();
        Endereço = (AutoCompleteTextView) v.findViewById(R.id.endereço);


        Endereço.setOnClickListener(mAutocompleteClickListener);

          /* ArrayAdapter<String> mAdapter= new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1,
                   act.client(), BOUNS_CAMPO_GRANDE, null );*/
        mAdapter = new PlaceAutocompleteAdapter(act, android.R.layout.simple_list_item_1,
                act.getmGoogleApiClient(), BOUNS_CAMPO_GRANDE, null);
        Endereço.setAdapter(mAdapter);
        PEndereço = Endereço.getText().toString();


        myToolbar = (Toolbar) v.findViewById(R.id.toolbar);


        nome=(TextView) v.findViewById(R.id.nomep);
        doenca=(TextView) v.findViewById(R.id.tdoencap);
        endereco=(TextView) v.findViewById(R.id.enderecop);
        blas=(TextView) v.findViewById(R.id.bla);

        nomeE =(EditText)v.findViewById(R.id.NomeP);
        enderecoE = (AutoCompleteTextView)v.findViewById(R.id.endereço);


        BancoDeDados helper= new BancoDeDados(getActivity());


        SQLiteDatabase banco = helper.getReadableDatabase();
        Cursor cursor = banco.query("lingua", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                switch (MenuDeEntrada.Linguaa) {
                    case "Português":
                       nome.setText("Nome Completo");
                        doenca.setText("Doença");
                        endereco.setText("Endereço");
                        nomeE.setHint("Ex: Maria Figueredo");
                        enderecoE.setHint("Ex: Rua Joaquim Murtinho, 586");
                        RegistrarCaso.setText("Registrar Caso");
                        blas.setText("Preencher os dados dos pacientes que possuem:Dengue, Zika Vírus, Chicungunya, NyongNyong ou Guilliant Barré.Preencher todos os campos");
                        break;
                    case "Español":
                        nome.setText("Nombre completo");
                        doenca.setText("Enfermedad");
                        endereco.setText("Dirección");
                        nomeE.setHint("Ex: Maria Consuela");
                        enderecoE.setHint("Ex: Rua Joaquim Murtinho, 586");
                        RegistrarCaso.setText("Register Caso");
                        blas.setText("Rellenar los datos de los pacientes que tienen : Dengue , virus Zika , Chikungunya , NyongNyong o Guilliant Barré.Preencher todos los campos");
                        break;

                    case "English":
                        nome.setText("Full name");
                        doenca.setText("Disease");
                        endereco.setText("Address");
                        nomeE.setHint("Ex: Mary Jones");
                        enderecoE.setHint("Ex: Street M.A, 586");
                        RegistrarCaso.setText("Register Case");
                        blas.setText("Fill the data of patients who have : Dengue , Zika virus , Chicungunya , NyongNyong or Guilliant Barré . Fill in all the fields");
                        break;

                    default:

                }

            } while (cursor.moveToNext());

        }


        return v;

    }


    public interface OnFragmentInteractionListener {
    }




}

