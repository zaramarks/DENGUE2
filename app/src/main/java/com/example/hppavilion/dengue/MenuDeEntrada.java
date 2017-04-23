package com.example.hppavilion.dengue;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MenuDeEntrada extends AppCompatActivity {


    public String L;
    public static String Linguaa;
    public Button cadastroo;
    public static String emaill, senhaa;
    private Toolbar toolbar;

    private GoogleApiClient client;

    ///
    public static EditText Email, Senha, string;
    //Salvar dados do BD


    public int conta = 0;

    public int contaa = 0;

    public BancoDeDados helper = new BancoDeDados(this);
    public Button btt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_de_entrada);
        // Sair.fa.finish();
        cadastroo = (Button) findViewById(R.id.button6);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Email = (EditText) findViewById(R.id.editText);
        Senha = (EditText) findViewById(R.id.editText2);
        BancoDeDados bd = new BancoDeDados(this);
        SQLiteDatabase banco = bd.getReadableDatabase();

        // banco.execSQL("DELETE FROM lingua"); //delete all rows in a table

        // banco.execSQL ("UPDATE lingua SET emaill = null");
        // banco.execSQL ("UPDATE lingua SET senhaa = null");

        Cursor cur = banco.rawQuery("SELECT EXISTS (SELECT 1 FROM lingua)", null);

        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt(0) == 0) {
                Intent intent = new Intent(MenuDeEntrada.this, Linguaa.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }


        Cursor cursor = banco.query("lingua", null, null, null, null, null, null);

        if (cursor.moveToLast()) {
            emaill = cursor.getString(cursor.getColumnIndex("emaill"));
            senhaa = cursor.getString(cursor.getColumnIndex("senhaa"));
            if (emaill != null && senhaa != null) {
                Intent intent = new Intent(MenuDeEntrada.this, NavigationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }
        if (cursor.moveToFirst()) {
            //  do {
            Linguaa = cursor.getString(cursor.getColumnIndex("linguaa"));

            switch (Linguaa) {
                case "Português":
                    cadastroo.setText("Cadastre-se");
                    Email.setHint("Email");
                    Senha.setHint("Senha");
                    break;
                case "Español":
                    cadastroo.setText("Registrar");
                    Email.setHint("correo electrónico");
                    Senha.setHint("contraseña");
                    // Toast.makeText(this, "El lenguaje cambia!", Toast.LENGTH_SHORT).show();
                    break;

                case "English":
                    cadastroo.setText("Register");
                    Email.setHint("Email");
                    Senha.setHint("Password");
                    //Toast.makeText(this, "Language changed!", Toast.LENGTH_SHORT).show();

                    break;

                default:
                    Email.setHint("Email");
                    Senha.setHint("Senha");
                    cadastroo.setText("Cadastree-se");
            }

            //   }while (cursor.moveToNext());


        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



       /* SQLiteDatabase bancoo = helper.getReadableDatabase();

        Cursor cursorr = bancoo.query("lingua", null, null, null, null, null, null);
        if (cursor.moveToLast()) {
            String email = cursorr.getString(cursorr.getColumnIndex("emaill"));
            String senha = cursorr.getString(cursorr.getColumnIndex("senhaa"));
            Log.e("oi", email);
            // Toast.makeText(this,"Email =" + email, Toast.LENGTH_SHORT).show();
            // Toast.makeText(this,"Senha =" + senha, Toast.LENGTH_SHORT).show();
            if (email != null && senha != null) {
                contaa = 1;
            }
            // Toast.makeText(this,"Conta =" + conta, Toast.LENGTH_SHORT).show();
        }*/

        }





    public void cadastro(View v) {
        Intent intent = new Intent(MenuDeEntrada.this, Registro.class);
        startActivity(intent);
    }

    /*public void login(View v) {
        Intent intent = new Intent(MenuDeEntrada.this,.class);
        startActivity(intent);


    }*/


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MenuDeEntrada Page", // TODO: Define a title for the content shown.
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
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MenuDeEntrada Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.hppavilion.dengue/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void LOGIN(View v) {

        String email1 = Email.getText().toString();
        String senha1 = Senha.getText().toString();

        BancoDeDados bd = new BancoDeDados(this);
        SQLiteDatabase banco = bd.getReadableDatabase();
        Cursor cursor = banco.query("usuario", null, null, null, null, null, null);
        String email, senha;

        if (cursor.moveToFirst()) {
            Log.e("Foi", "quase tudo");
            do {

                email = cursor.getString(cursor.getColumnIndex("email"));
                senha = cursor.getString(cursor.getColumnIndex("senha"));

                Log.e("BD", "entrou no banco de dadaos");

                if (email.equals(email1) && senha.equals(senha1)) {

                        SQL c = new SQL();
                        c.setemaill(email1);
                        c.setSenhaa(senha1);
                        helper.insertContacttt(c);
                   // banco.execSQL ("UPDATE lingua SET emaill = null");
                  //  banco.execSQL ("UPDATE lingua SET senhaa = null");
                 // banco.execSQL("UPDATE lingua SET emaill = xx WHERE id=1 ");
                  //  banco.execSQL("UPDATE lingua SET senhaa = xxx WHERE id=1 ");

                    Intent a = new Intent(MenuDeEntrada.this, NavigationActivity.class);
                    startActivity(a);
                    finish();
                    conta = 1;
                    break;

                }

            } while (cursor.moveToNext());


        }

        if (conta == 0) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Email ou senha incorretos");
            dlg.setNeutralButton("OK", null);
            AlertDialog dialog = dlg.create();
            dialog.show();

        }
    }

    }


