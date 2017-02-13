package com.example.hppavilion.dengue.fragmentos;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hppavilion.dengue.BancoDeDados;
import com.example.hppavilion.dengue.NavigationActivity;
import com.example.hppavilion.dengue.R;
import com.example.hppavilion.dengue.adapter.AddAdapter;
import com.example.hppavilion.dengue.adapter.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP PAVILION on 08/04/2016.
 */
public class CasosAdicionados extends Fragment  {
    public static ListView lstDados;
    public static ArrayAdapter<String> adpDados;
    private AddAdapter adapter;
    private List<ItemSlideMenu> listSliding;
    private ArrayList<String> results = new ArrayList<String>();
    NavigationActivity act = new NavigationActivity();
    public SearchView sv;
    private static final String campos[] = {"Pnome","Pdoenca", "Pendereco", "Pid"};
    private CursorAdapter dataSource;
    private ArrayList<String> nome= new ArrayList<>();
    private ArrayList<String> endereco = new ArrayList<>();
    private ArrayList<Integer> doenca = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.casos_adicionados, container, false);

         NavigationActivity.sv.setVisibility(View.INVISIBLE);
         lstDados = (ListView)v.findViewById(R.id.listView);

        BancoDeDados bd = new BancoDeDados(getActivity());
        SQLiteDatabase banco = bd.getReadableDatabase();
        lstDados.setAdapter(new AddAdapter(getActivity(), doenca, nome, endereco));
        Cursor cursor = banco.query("Casos", null, null, null, null, null, null);
    if (cursor.moveToFirst()) {
       do {
            String pnome = cursor.getString(cursor.getColumnIndex("Pnome"));
            String pdoenca = cursor.getString(cursor.getColumnIndex("Pdoenca"));
            String pendereco = cursor.getString(cursor.getColumnIndex("Pendereco"));


//            Toast.makeText(getActivity(),pendereco, Toast.LENGTH_SHORT).show();

               switch (pdoenca){
                    case "Dengue":
                        doenca.add(R.mipmap.red);
                            break;
                    case "Zika vírus":
                        doenca.add(R.mipmap.green);
                        break;
                    case "Chikungunya":
                        doenca.add(R.mipmap.blue);
                        break;
                    case "Guillaint barré":
                        doenca.add(R.mipmap.yellow);
                        break;
                    case "Nyongnyong":
                        doenca.add(R.mipmap.orange);
                        break;
                    default:
                        doenca.add(R.mipmap.point);
                        Toast.makeText(getActivity(),pdoenca, Toast.LENGTH_SHORT).show();

                }
            nome.add(pnome +" - "+pdoenca);
            endereco.add(pendereco);
        } while (cursor.moveToNext());
    }

        //adapter = new AddAdapter(getActivity(), listSliding);
       // lstDados.setAdapter(adapter);


      /*  Cursor contatos = database.query("Casos", campos, null, null, null, null, null);


            //cria cursor que será exibido na tela, nele serão exibidos
            //todos os contatos cadastrados
            dataSource = new SimpleCursorAdapter(getActivity(), R.layout.list_item, contatos,
                    campos, new int[] { R.id.NomeList, R.id.DoencaList , R.id.EnderecoList});

            //relaciona o dataSource ao próprio listview
            lstDados.setAdapter(dataSource);*/
/*        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                EditText searchEditText = (EditText) sv.findViewById(android.support.v7.appcompat.R.id.search_src_text);
                Log.v("Pato", String.valueOf(searchEditText));


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("Pes", "B");
                return false;
            }
        });*/
        return v;
    }



    public interface OnFragmentInteractionListener {
    }
}
