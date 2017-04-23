package com.example.hppavilion.dengue;


import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hppavilion.dengue.adapter.CasoAdapter;
import com.example.hppavilion.dengue.adapter.ItemSlideMenu;
import com.example.hppavilion.dengue.fragmentos.AddCasos;
import com.example.hppavilion.dengue.fragmentos.CasosAdicionados;
import com.example.hppavilion.dengue.fragmentos.Configuracao;
import com.example.hppavilion.dengue.fragmentos.Informacoes;
import com.example.hppavilion.dengue.fragmentos.Mapa;
import com.example.hppavilion.dengue.fragmentos.Sair;
import com.example.hppavilion.dengue.fragmentos.addFocos;
import com.example.hppavilion.dengue.fragmentos.buscar;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import com.example.hppavilion.dengue.adapter.AddAdapter;
//import com.example.hppavilion.dengue.model.BancoDeDados2;


public class NavigationActivity extends ActionBarActivity
        implements Mapa.NavigationDrawerCallBack, AddCasos.OnFragmentInteractionListener, Informacoes.OnFragmentInteractionListener,
        CasosAdicionados.OnFragmentInteractionListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, Sair.OnFragmentInteractionListener,
        Configuracao.OnFragmentInteractionListener, resultadoBusca.OnFragmentInteractionListener, addFocos.OnFragmentInteractionListener


{

    private static final float DEFAULTZOM = 18;
    private List<ItemSlideMenu> listSliding;
    private CasoAdapter adapter;
    private ListView listViewSliding, LView;
    private DrawerLayout drawerLayout;
    private RelativeLayout relativeLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    public TextView nome;
    public TextView email;
    public SupportMapFragment mFragMap = null;
    public GoogleApiClient mGoogleApiClient = null;
    public String nomeP, doencaP, enderecoP;
    BancoDeDados helper2 = new BancoDeDados(this);
    private static final double CG_LAT = -20.4435,
            CG_LGT = -54.6478;

    public static GoogleMap mMap;
    public GoogleApiClient client;
        public FragmentManager fm = getSupportFragmentManager();
    public static String PPDOENCA, PPENDERECO, PPNOME, PPPENDERECO;
    public double Plat1;
    public double Plng2;
    public static SearchView sv;
    public BancoDeDados helper = new BancoDeDados(this);
    public int valor = 0;
    public static String PNOME;
    public static String PSOBRENOME;
    public static String PEMAIL;
    public static String PENDERECO;
    public static double PLAT;
    public static double PLNG;
    public static LatLng latlng;
    public String emaill, senhaa;
    public static LatLng pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        sv = (SearchView) findViewById(R.id.searchView2);

        Intent searchIntent = getIntent();
        if (Intent.ACTION_SEARCH.equals(searchIntent.getAction())) {
            String query = searchIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(NavigationActivity.this, query, Toast.LENGTH_SHORT).show();

        }
        buildGoogleApiClient();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        relativeLayout = (RelativeLayout) findViewById(R.id.main_content);
        listViewSliding = (ListView) findViewById(R.id.lv);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nome = (TextView) findViewById(R.id.NOME);
        email = (TextView) findViewById(R.id.EMAIL);

        //   email.setText(PEMAIL);


        SQLiteDatabase banco = helper.getReadableDatabase();
        Cursor cursor = banco.query("lingua", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            String Linguaa = cursor.getString(cursor.getColumnIndex("linguaa"));
            do {

                switch (Linguaa) {
                    case "Português":
                        listSliding = new ArrayList<>();
                        listSliding.add(new ItemSlideMenu(R.drawable.place, "  Mapa "));
                        listSliding.add(new ItemSlideMenu(R.drawable.add, "  Adicionar Casos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.add, "  Adicionar Focos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.list, "  Meus Casos Adicionados"));
                        listSliding.add(new ItemSlideMenu(R.drawable.search, "  Buscar Casos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.info, "  Informações"));
                        listSliding.add(new ItemSlideMenu(R.drawable.settings, "  Configurações"));
                        listSliding.add(new ItemSlideMenu(R.drawable.logoff, "  Logout"));
                        adapter = new CasoAdapter(this, listSliding);
                        listViewSliding.setAdapter(adapter);

                        break;
                    case "Español":
                        listSliding = new ArrayList<>();
                        listSliding.add(new ItemSlideMenu(R.drawable.place, "   Mapa "));
                        listSliding.add(new ItemSlideMenu(R.drawable.ic_menu_send, "Añadir casos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.add, "  Añadir focos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.ic_menu_slideshow, "Mi casos añadidos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.search, "  Buscar Casos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.ic_menu_gallery, "Información"));
                        listSliding.add(new ItemSlideMenu(R.drawable.ic_menu_manage, "  Ajustes"));
                        listSliding.add(new ItemSlideMenu(R.drawable.ic_menu_share, "  Logout"));
                        adapter = new CasoAdapter(this, listSliding);
                        listViewSliding.setAdapter(adapter);

                        break;

                    case "English":
                        listSliding = new ArrayList<>();
                        listSliding.add(new ItemSlideMenu(R.drawable.place, "   Map "));
                        listSliding.add(new ItemSlideMenu(R.drawable.add, "  Add Cases"));
                        listSliding.add(new ItemSlideMenu(R.drawable.add, "  Add Focos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.list, "  My Cases Added"));
                        listSliding.add(new ItemSlideMenu(R.drawable.search, "  Search Cases"));
                        listSliding.add(new ItemSlideMenu(R.drawable.info, "  Informations"));
                        listSliding.add(new ItemSlideMenu(R.drawable.settings, "  Configurations"));
                        listSliding.add(new ItemSlideMenu(R.drawable.logoff, "  Logout"));

                        adapter = new CasoAdapter(this, listSliding);
                        listViewSliding.setAdapter(adapter);
                        break;

                    default:
                        listSliding = new ArrayList<>();
                        listSliding.add(new ItemSlideMenu(R.drawable.place, "  Mapa "));
                        listSliding.add(new ItemSlideMenu(R.drawable.add, "  Adicionar casos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.add, "  Adicionar Focos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.list, "  Meus casos adicionados"));
                        listSliding.add(new ItemSlideMenu(R.drawable.search, "  Buscar Casos"));
                        listSliding.add(new ItemSlideMenu(R.drawable.info, "  informações"));
                        listSliding.add(new ItemSlideMenu(R.drawable.settings, "  Configurações"));
                        listSliding.add(new ItemSlideMenu(R.drawable.logoff, "  Logout"));
                        adapter = new CasoAdapter(this, listSliding);
                        listViewSliding.setAdapter(adapter);
                }

            } while (cursor.moveToNext());

        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //set title
        setTitle(listSliding.get(0).getTitle());
        //item selected
        listViewSliding.setItemChecked(0, true);
        //Displays fragmento1 when it starts
        replaceFragment(0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle togglee = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(togglee);
        togglee.syncState();

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //set title
                setTitle(listSliding.get(position).getTitle());
                //item selected
                listViewSliding.setItemChecked(position, true);
                //replace fragement
                replaceFragment(position);
                //close list
                // drawerLayout.closeDrawer(listViewSliding);

            }

        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //  LView = (ListView)findViewById(R.id.)


    }


    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    //Create method replace fragment


    public void replaceFragment(int pos) {
        Fragment fragment = null;
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        String title = "";
        switch (pos) {
            case 0:


               mFragMap = new SupportMapFragment();
                mFragMap.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        mMap = googleMap;

                        try {
                            configuraMapa();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //gotoLocation(CG_LAT,CG_LGT, DEFAULTZOM);
                    }


                });
                // getFragmentManager().popBackStack();
                valor = 1;
                fm.beginTransaction()

                        .replace(R.id.Container, mFragMap)
                        // .addToBackStack("fragBack")

                        .commit();

                //ft.add(R.id.Container, mFragMap, "mapa");
                // ft.addToBackStack("topMapa");
                // ft.commit();

                break;

            case 1:
                valor = 0;
                fragment = new AddCasos();
                // getFragmentManager().popBackStack();
                fm.beginTransaction()
                        .replace(R.id.Container, fragment)
                        .addToBackStack("fragBack")
                        .commit();

                break;
            case 2:
                valor = 0;
                fragment = new addFocos();
                // getFragmentManager().popBackStack();
                fm.beginTransaction()
                        .replace(R.id.Container, fragment)
                        .addToBackStack("fragBack")
                        .commit();

                break;
            case 3:
                valor = 0;
                fragment = new CasosAdicionados();
                //  Listar();
                //  getFragmentManager().popBackStack();
                fm.beginTransaction()
                        .replace(R.id.Container, fragment)
                        .addToBackStack("fragBack")
                        .commit();

                break;
            case 4:
                valor = 0;
                fragment = new buscar();
                // getFragmentManager().popBackStack();
                fm.beginTransaction()
                        .replace(R.id.Container, fragment)
                        .addToBackStack("fragBack")
                        .commit();

                break;
            case 5:
                valor = 0;
                fragment = new Informacoes();
                fm.beginTransaction()
                        .replace(R.id.Container, fragment)
                        .addToBackStack("fragBack")
                        .commit();

                break;
            case 6:
                valor = 0;
                fragment = new Configuracao();
                //  getFragmentManager().popBackStack();
                fm.beginTransaction()
                        .replace(R.id.Container, fragment)
                        .addToBackStack("fragBack")
                        .commit();

                break;
            case 7:
                valor = 0;
                fragment = new Sair();
                //  getFragmentManager().popBackStack();
                fm.beginTransaction()
                        .replace(R.id.Container, fragment)
                        .addToBackStack("fragBack")
                        .commit();

                break;
            default:
                valor = 0;
                fragment = new Mapa();
                // getFragmentManager().popBackStack();
                fm.beginTransaction()
                        .replace(R.id.Container, fragment)
                        //.addToBackStack("fragBack")
                        .commit();

                break;
        }


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    private void configuraMapa() throws IOException {

        BancoDeDados bd = new BancoDeDados(this);
        SQLiteDatabase banco = bd.getReadableDatabase();

        Cursor cursor = banco.query("lingua", null, null, null, null, null, null);
        if (cursor.moveToLast()) {
            emaill = cursor.getString(cursor.getColumnIndex("emaill"));
            senhaa = cursor.getString(cursor.getColumnIndex("senhaa"));
            email.setText(emaill);
            Log.v("BD lingua email", emaill);
            Log.v("BD lingua senha", senhaa);
        }


        SQLiteDatabase bancoo = bd.getReadableDatabase();
        Cursor cursorr = bancoo.query("usuario", null, null, null, null, null, null);
        if (cursorr.moveToFirst()) {
            do {
                String email = cursorr.getString(cursorr.getColumnIndex("email"));
                String senha = cursorr.getString(cursorr.getColumnIndex("senha"));
                Log.v("BD usuario email", email);
                Log.v("BD usuario senha", senha);
                if (email.equals(emaill) && senha.equals(senhaa)) {
                    String aa = cursorr.getString(cursorr.getColumnIndex("id"));
                    PNOME = cursorr.getString(cursorr.getColumnIndex("nome"));
                    PSOBRENOME = cursorr.getString(cursorr.getColumnIndex("sobrenome"));
                    PENDERECO = cursorr.getString(cursorr.getColumnIndex("endereco"));
                    PLAT = cursorr.getDouble(cursorr.getColumnIndex("lat"));
                    PLNG = cursorr.getDouble(cursorr.getColumnIndex("lng"));

                    latlng = new LatLng(PLAT, PLNG);
                }
            } while (cursorr.moveToNext());
        }

        final CameraPosition cp = new CameraPosition.Builder().target(latlng).zoom(13).bearing(0).tilt(00).build();
        CameraUpdate cam = CameraUpdateFactory.newCameraPosition(cp);
        mMap.moveCamera(cam);

        //  addAdapter = new AddAdapter(ctx, R.layout.display_product_row);
        Cursor cursore = banco.query("Casos", null, null, null, null, null, null);
        while (cursore.moveToNext()) {
            Log.e("Condiçao", "entrou");
            nomeP = cursore.getString(cursore.getColumnIndex("Pnome"));
            doencaP = cursore.getString(cursore.getColumnIndex("Pdoenca"));
            enderecoP = cursore.getString(cursore.getColumnIndex("Pendereco"));
            Plat1 = cursore.getDouble(cursore.getColumnIndex("Plat"));
            Plng2 = cursore.getDouble(cursore.getColumnIndex("Plng"));

            final double plaat = Plat1;
            final double plnng = Plng2;


            pos = new LatLng(plaat, plnng);

            Log.e("Condiçao", "ENTROU");
            if (doencaP.equals("Dengue")) {
                MarkerOptions options = new MarkerOptions()
                        .title(nomeP + " - Dengue")
                        .snippet(enderecoP)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED))

                        .position(pos);

                mMap.addMarker(options);

            } else if (doencaP.equals("Zika vírus")) {
                MarkerOptions options = new MarkerOptions()
                        .title(nomeP + " - Zika vírus")
                        .snippet(enderecoP)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN))

                        .position(pos);

                mMap.addMarker(options);

            } else if (doencaP.equals("Chikungunya")) {
                MarkerOptions options = new MarkerOptions()
                        .title(nomeP + " - Chicungunya")
                        .snippet(enderecoP)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_AZURE))
                        .position(pos);

                mMap.addMarker(options);
                Foco(nomeP);
            } else if (doencaP.equals("Nyongnyong")) {
                MarkerOptions options = new MarkerOptions()
                        .title(nomeP + " - Nyongnyong")
                        .snippet(enderecoP)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_ORANGE))
                        .position(pos);

                mMap.addMarker(options);

            } else if (doencaP.equals("Guillain barré")) {
                MarkerOptions options = new MarkerOptions()
                        .title(nomeP + " - Guillain barré")
                        .snippet(enderecoP)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_YELLOW))
                        .position(pos);

                mMap.addMarker(options);

            } else if (doencaP.equals("Foco")) {
               /* MarkerOptions options = new MarkerOptions()
                        .title("Foco")
                        .snippet(enderecoP)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HU))
                        .position(pos);

                mMap.addMarker(options);*/
                drawCircle(pos);

            }


        }
/* MarkerOptions options = (new MarkerOptions()
                .getPosition(lat, lng)
                .setTitle("Campo Grande")
                .SetSnipped("AKA whatever"));*/


    }

    public void PinarMapa(View v) throws IOException {
        try {
            PPNOME = AddCasos.Nome.getText().toString();
            //AddCasos.NOME += " - "   +  AddCasos.SpnOpcoes.getSelectedItem();
            // DOENCA=Doenca.getText().toString();

            PPENDERECO = AddCasos.Endereço.getText().toString();
            //   Log.e("Endereco",PPENDERECO );
            PPDOENCA = (String) AddCasos.SpnOpcoes.getSelectedItem();
            Geocoder gcc = new Geocoder(this);
            List<Address> list = gcc.getFromLocationName(PPENDERECO, 1);
            Address add = list.get(0);
            String locality = add.getLocality();


            final double latt = add.getLatitude();

            final double lngg = add.getLongitude();

            // latlng2 = new LatLng(latt, lngg);

            SQL a = new SQL();
            a.setPnome(PPNOME);
            a.setPdoenca(PPDOENCA);
            a.setPendereco(PPENDERECO);
            a.setPlat(latt);
            a.setPlng(lngg);
            helper2.insertContactt(a);
            // addAdapter.add(a);

            Toast.makeText(this, "Caso registrado!", Toast.LENGTH_SHORT).show();

            AddCasos.Nome.setText("");
            AddCasos.Endereço.setText("");
        } catch (IOException e) {
            Toast.makeText(this, " O caso não pode ser registrado. Conecte-se a internet, e tente novamente.", Toast.LENGTH_SHORT).show();
        }


    }


    public GoogleApiClient getmGoogleApiClient() {
        if (mGoogleApiClient == null) {
            buildGoogleApiClient();
        }
        return mGoogleApiClient;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Navigation Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.hppavilion.dengue/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }


    @Override
    public void onBackPressed() {
        if (valor == 1) {
            new AlertDialog.Builder(this)
                    .setTitle("Deseja sair do MapAedes?")
                    .setMessage("Caso saia, você não seja deslogado de sua conta")
                    .setPositiveButton("SIM",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })
                    .setNegativeButton("NÃO",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            }).show();
        } else {
            Intent i = new Intent(this, NavigationActivity.class);
            startActivity(i);
        }

    }

    public void Foco(String nome) {
        if (nome.equals("Foco")) {
            drawCircle(pos);
        }
    }


    public static void drawCircle(LatLng point) {

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle
        circleOptions.radius(500);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(0x550000);

        // Border width of the circle
        circleOptions.strokeWidth(2);

        // Adding the circle to the GoogleMap
        mMap.addCircle(circleOptions);

    }

    public void ADICIONARFOCO(View view) {

        Toast.makeText(this, addFocos.tipo, Toast.LENGTH_SHORT).show();
        String teste = addFocos.endereço.getText().toString().toLowerCase();
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> list = null;
        try {
            list = geocoder.getFromLocationName(teste, 1);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Address add = list.get(0);

        final double lattt = add.getLatitude();

        final double lnggg = add.getLongitude();
        String te = "Foco";
        Log.v("latitude", String.valueOf(lattt));
        SQL ah = new SQL();
        ah.setPnome(te);
        ah.setPdoenca(te);
        ah.setPendereco(teste);
        ah.setPlat(lattt);
        ah.setPlng(lnggg);
        helper.insertContactt(ah);
        Toast.makeText(this, "Foco registrado!", Toast.LENGTH_SHORT).show();
    }


}




