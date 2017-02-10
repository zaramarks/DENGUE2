package com.example.hppavilion.dengue;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.database.*;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hppavilion.dengue.adapter.PlaceAutocompleteAdapter;
import com.example.hppavilion.dengue.fragmentos.AddCasos;

import java.io.IOException;
import java.util.List;

public class Registro extends AppCompatActivity {
        public static TextView NOME, SOBRENOME, EMAIL, SENHA,  ENDERECO, CIDADE;
    public static EditText NOME1, SOBRENOME1, EMAIL1, SENHA1, ENDERECO1, CIDADE1;
        public static String REMAIL, RSENHA, RNOME, RSOBRENOME, RCIDADE, RENDERECO, tipo;
        public  static double latRe, lngRe;
        public BancoDeDados myDb;
        private SQLiteDatabase conn;
        public static Button registro;
        private static final String REGISTER_URL = "http://aedesaegyptizara.esy.es/register.php";
     //    public AutoCompleteTextView CIDADE;
        private PlaceAutocompleteAdapter mAdapterr;

        public static Double lat1, lng1;
    BancoDeDados helper = new BancoDeDados(this);
    View.OnClickListener mAutocompleteClickListenerr;
    public  ArrayAdapter<String> adpOpcoes;
    public  Spinner SpnOpcoes;
    //private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       myDb=new BancoDeDados(this);

        NOME = (TextView) findViewById(R.id.textView4);
        SOBRENOME = (TextView) findViewById(R.id.textView5);
        EMAIL = (TextView) findViewById(R.id.textView6);
        SENHA = (TextView) findViewById(R.id.textView8);
        CIDADE = (TextView)findViewById(R.id.textView9);
      ;
       // CIDADE =(AutoCompleteTextView)findViewById(R.id.add2);
        ENDERECO = (EditText)findViewById(R.id.add);
        registro= (Button)findViewById(R.id.button3);


        NOME1 = (EditText)findViewById(R.id.editText3);
        SOBRENOME1 = (EditText)findViewById(R.id.editText4);
        EMAIL1 = (EditText)findViewById(R.id.editText5);
        SENHA1 = (EditText)findViewById(R.id.editText6);
        ENDERECO1 = (EditText)findViewById(R.id.add);

        BancoDeDados bd = new BancoDeDados(this);
        SQLiteDatabase banco = bd.getReadableDatabase();

        SpnOpcoes = (Spinner) findViewById(R.id.spinner2);


        adpOpcoes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnOpcoes.setAdapter(adpOpcoes);


        adpOpcoes.add("Médico");
        adpOpcoes.add("Agente de saúde");
        adpOpcoes.add("Agente de posto");
        adpOpcoes.add("População");

        Cursor cursor = banco.query("lingua", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
               // Toast.makeText(this, MenuDeEntrada.Linguaa, Toast.LENGTH_SHORT).show();
                switch (MenuDeEntrada.Linguaa) {
                    case "Português":
                        Registro.NOME.setHint("Nome");
                        Registro.SOBRENOME.setText("Sobrenome");
                        Registro.EMAIL.setText("Email");
                        Registro.SENHA.setText("Senha");
                        Registro.CIDADE.setText("Cidade, Estado");
                        Registro.registro.setText("Cadastre-se");
                        break;
                    case "Español":
                        Registro.NOME.setText("Nombre");
                        Registro.SOBRENOME.setText("Apellido");
                        Registro.EMAIL.setText("Correo Eletrónico");
                        Registro.SENHA.setText("Contraseña");
                        Registro.CIDADE.setText("Ciudad, Estado");
                        Registro.registro.setText("Registrar");
                        break;


                    case "English":
                        Registro.NOME.setText("Name");
                        Registro.SOBRENOME.setText("Surname");
                        Registro.EMAIL.setText("Email");
                        Registro.SENHA.setText("Password");
                        Registro.CIDADE.setText("City, State");
                        Registro.registro.setText("Register");
                        break;

                    default:
                        Registro.NOME.setHint("Nome");
                        Registro.SOBRENOME.setText("Sobrenome");
                        Registro.EMAIL.setText("Email");
                        Registro.SENHA.setText("Senha");
                        Registro.CIDADE.setText("Cidade, Estado");
                        Registro.registro.setText("Cadastre-se");
                }

            } while (cursor.moveToNext());

        }




    }
    public void CONFIRMAR (View v) throws IOException {

       /* REMAIL = EMAIL.getText().toString().trim().toLowerCase();
        RSENHA = SENHA.getText().toString().trim().toLowerCase();
        RNOME = NOME.getText().toString().trim().toLowerCase();
        RSOBRENOME = SOBRENOME.getText().toString().trim().toLowerCase();*/
        REMAIL = EMAIL1.getText().toString();
        RSENHA = SENHA1.getText().toString();
        RNOME = NOME1.getText().toString();
        RSOBRENOME = SOBRENOME1.getText().toString();
        RENDERECO =ENDERECO1.getText().toString();
        tipo= (String) SpnOpcoes.getSelectedItem();
        Log.v("ENDERECO", RENDERECO);

        //Mapa

        EMAIL.setError(null);
        SENHA.setError(null);
        NOME.setError(null);
       SOBRENOME.setError(null);
        ENDERECO.setError(null);

    // register(RNOME,RSOBRENOME,RSENHA ,REMAIL );

        boolean cancel = false;
        View focusView = null;
        int num =0;
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(RNOME)){
            NOME.setError(getString(R.string.error_field_required));
            focusView = NOME;
            cancel = true;
            num =1;
        }
        if (TextUtils.isEmpty(RSOBRENOME)){
       SOBRENOME.setError(getString(R.string.error_field_required));
            focusView = SOBRENOME;
            cancel = true;
            num =1;
    }
        if (TextUtils.isEmpty(RENDERECO)){
            ENDERECO.setError(getString(R.string.error_field_required));
            focusView = ENDERECO;
            cancel = true;
            num =1;
        }
        if (TextUtils.isEmpty(RSENHA)) {

            SENHA.setError(getString(R.string.error_field_required));
            focusView = SENHA;
            cancel = true;
            num =1;
        } else if (!isPasswordValid(RSENHA)) {
            SENHA.setError(getString(R.string.error_invalid_password));
            focusView = SENHA;
            cancel = true;
            num =1;
        }
            // Check for a valid email address.
            if (TextUtils.isEmpty(REMAIL)) {
                EMAIL.setError(getString(R.string.error_field_required));
                focusView = EMAIL;
                cancel = true;
                num =1;
            } else if (!isEmailValid(REMAIL)) {
                EMAIL.setError(getString(R.string.error_invalid_email));
                focusView = EMAIL;
                cancel = true;
                num =1;
            }


            //Codigo feito



        Log.e("endereco", RENDERECO);
            if (isPasswordValid(RSENHA) && isEmailValid(REMAIL)) {
                if ((RNOME != null )&& (RSOBRENOME != null )&& (REMAIL != null ) && (RSENHA != null )&& (RENDERECO != null ) ){
        try {
            Geocoder gcc = new Geocoder(this);
            List<Address> list = gcc.getFromLocationName(RENDERECO, 1);
            Address add = list.get(0);
            String locality = add.getLocality();
            Toast.makeText(this, locality, Toast.LENGTH_LONG).show();

            final double latt = add.getLatitude();

            final double lngg = add.getLongitude();

            SQL c = new SQL();
            c.setNome(RNOME);
            c.setSobrenome(RSOBRENOME);
            c.setEmail(REMAIL);
            c.setSenha(RSENHA);
            c.setEndereço(RENDERECO);
            c.setLat(latt);
            c.setLng(lngg);
            c.setTipo(tipo);

            helper.insertContact(c);
            Toast toast = Toast.makeText(Registro.this, "Vocè foi cadastrado!", Toast.LENGTH_LONG);
            toast.show();
            Intent intentt = new Intent(Registro.this, MenuDeEntrada.class);
            startActivity(intentt);
            finish();

        }catch (IOException E){
                Log.e("erro", "denovo", E);
                    Toast toast = Toast.makeText(Registro.this, "Conecte-se a internet, e tente novamente mais tarde", Toast.LENGTH_LONG);
                    toast.show();
                }

                }else{
                    Toast toast = Toast.makeText(Registro.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 10,10);
                    toast.show();
                }


            }else {
                Toast toast = Toast.makeText(Registro.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 10, 10);
                toast.show();

            }

        }



    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


}
