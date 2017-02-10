package com.example.hppavilion.dengue;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hppavilion.dengue.adapter.AddAdapter;
import com.example.hppavilion.dengue.fragmentos.buscar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by zazah on 08/08/2016.
 */
public class resultadoBusca extends AppCompatActivity {
        private Toolbar toolbar;
    public static String d, e, n;
    private ArrayList<String> Anome= new ArrayList<>();
    private ArrayList<String> Aendereco = new ArrayList<>();
    private ArrayList<Integer> Adoenca = new ArrayList<>();
    private ListView lv;
    public int a=0;
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

        BancoDeDados bd = new BancoDeDados(this);
        SQLiteDatabase banco= bd.getReadableDatabase();

        n= buscar.etNOME.getText().toString();
        d=buscar.etDOENCA.getSelectedItem().toString();
        //Toast.makeText(this,d, Toast.LENGTH_SHORT).show();
        e=buscar.etENDERECO.getText().toString().toLowerCase();
        lv = (ListView)findViewById(R.id.buscaas);
        lv.setAdapter(new AddAdapter(this, Adoenca, Anome, Aendereco));
      //  nome.clear();
       // doenca.clear();
       // endereco.clear();
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> list = null;


        Cursor cursor = banco.query("Casos", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                 nomee = cursor.getString(cursor.getColumnIndex("Pnome"));
                 doencaa = cursor.getString(cursor.getColumnIndex("Pdoenca"));
                 enderecoo = cursor.getString(cursor.getColumnIndex("Pendereco"));
                 lat = Double.valueOf(cursor.getString(cursor.getColumnIndex("Plat")));
                 lng = Double.valueOf(cursor.getString(cursor.getColumnIndex("Plng")));

                if (buscar.c == 1) {

                    //DIGITOU
                    try {
                        list = geocoder.getFromLocationName(e, 1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Address add = list.get(0);

                    final double lattt = add.getLatitude();

                    final double lnggg = add.getLongitude();

                    //BD
                    try {
                        addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    String address = addresses.get(0).getAddressLine(0).toLowerCase();
                    String city = addresses.get(0).getLocality().toLowerCase();
                    String state = addresses.get(0).getAdminArea().toLowerCase();
                    String country = addresses.get(0).getCountryName().toLowerCase();

                    //COUNTRY
                    List<Address> listt = null;

                    try {
                        listt = geocoder.getFromLocationName(country, 1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Address addd = listt.get(0);
                    final double latt = addd.getLatitude();

                    final double lngg = addd.getLongitude();

                    if (latt == lattt && lngg ==lnggg){

                        ponteiro();
                    }

                    //STATE
                    try {
                        listt = geocoder.getFromLocationName(state, 1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Address ad = listt.get(0);
                    final double lattitute = ad.getLatitude();

                    final double lnggidute = ad.getLongitude();

                    if (lattitute == lattt && lnggidute ==lnggg){
                        ponteiro();
                    }

                    //CITY
                    try {
                        listt = geocoder.getFromLocationName(city, 1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Address a = listt.get(0);
                    final double latitude = a.getLatitude();

                    final double lngidute = a.getLongitude();

                    if (latitude == lattt && lngidute ==lnggg){

                        ponteiro();

                    }

                    //Address
                    try {
                        listt = geocoder.getFromLocationName(address, 1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Address addresss = listt.get(0);
                    final double latitud = addresss.getLatitude();

                    final double lngidut = addresss.getLongitude();

                    if (latitud == lattt && lngidut ==lnggg){

                        ponteiro();

                    }
                }
                    if (buscar.b == 1) {
                        if (doencaa.equals(d)) {

                            ponteiro();
                        }
                    }


            }while (cursor.moveToNext()) ;

        }
         Toast.makeText(this,a +" caso(s) encontrado(s)", Toast.LENGTH_SHORT).show();

    }

    private void ponteiro() {
        a++;
        switch (doencaa) {
            case "Dengue":
                Adoenca.add(R.mipmap.red);
                //Toast.makeText(this,nomee, Toast.LENGTH_SHORT).show();
                break;
            case "Zika vírus":
                Adoenca.add(R.mipmap.green);
                // Toast.makeText(this,nomee, Toast.LENGTH_SHORT).show();
                break;
            case "Chikungunya":
                Adoenca.add(R.mipmap.blue);
                break;
            case "Guillaint barré":
                Adoenca.add(R.mipmap.yellow);
                break;
            case "Nyongnyong":
                Adoenca.add(R.mipmap.orange);
                break;
            default:
                Adoenca.add(R.mipmap.red);
                Toast.makeText(this,doencaa, Toast.LENGTH_SHORT).show();

        }
        Anome.add(nomee + " - " + doencaa);
        Aendereco.add(enderecoo);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
