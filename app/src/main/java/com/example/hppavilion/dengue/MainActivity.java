/*package com.example.hppavilion.dengue;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {
   public static  EditText Email, Senha, string;
    //Salvar dados do BD


    public int conta =0;

    public int contaa =0;

   public BancoDeDados helper= new BancoDeDados(this);
    public Button btt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setHomeButtonEnabled(true);


        Email = (EditText) findViewById(R.id.editText);
        Senha = (EditText) findViewById(R.id.editText2);


        SQLiteDatabase banco = helper.getReadableDatabase();

        Cursor cursor = banco.query("lingua", null, null, null, null, null, null);
        if (cursor.moveToLast()) {

                String email = cursor.getString(cursor.getColumnIndex("emaill"));
                String senha = cursor.getString(cursor.getColumnIndex("senhaa"));
               // Toast.makeText(this,"Email =" + email, Toast.LENGTH_SHORT).show();
               // Toast.makeText(this,"Senha =" + senha, Toast.LENGTH_SHORT).show();
              if (email != null && senha!=null){
                  contaa=1;
              }
           // Toast.makeText(this,"Conta =" + conta, Toast.LENGTH_SHORT).show();
        }
            if (cursor.moveToFirst()) {
                do {

                    switch (MenuDeEntrada.Linguaa) {
                        case "Português":
                            Email.setHint("Email");
                            Senha.setHint("Senha");
                            break;
                        case "Español":
                            Email.setHint("correo electrónico");
                            Senha.setHint("contraseña");
                            break;

                        case "English":
                            Email.setHint("Email");
                            Senha.setHint("Password");
                            break;

                        default:
                            Email.setHint("Email");
                            Senha.setHint("Senha");
                    }

                } while (cursor.moveToNext());

            }


        }

   public void LOGIN(View v) {

       String email1 = Email.getText().toString();
       String senha1 = Senha.getText().toString();

       BancoDeDados bd = new BancoDeDados(this);
       SQLiteDatabase banco= bd.getReadableDatabase();
       Cursor cursor = banco.query("usuario",null, null, null, null, null, null);
        String email, senha;

        if (cursor.moveToFirst()){
            Log.e("Foi", "quase tudo");
            do {

                email = cursor.getString(cursor.getColumnIndex("email"));
                senha =cursor.getString(cursor.getColumnIndex("senha"));
                Log.e("BD", "entrou no banco de dadaos");

                if (email.equals(email1) && senha.equals(senha1)){
                    if (contaa==0) {
                        SQL c = new SQL();
                        c.setemaill(email1);
                        c.setSenhaa(senha1);
                        helper.insertContacttt(c);
                    }else{
                        banco.execSQL ("UPDATE lingua SET emaill = null");
                        banco.execSQL ("UPDATE lingua SET senhaa = null");
                        SQL c = new SQL();
                        c.setemaill(email1);
                        c.setSenhaa(senha1);
                        helper.insertContacttt(c);
                        Log.e("BD", "else");
                    }

                        Intent a = new Intent(MainActivity.this, NavigationActivity.class);
                      //   a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(a);
                    finish();
                    conta =1;
                    break;

                }

            }while(cursor.moveToNext());


        }

       if (conta ==0) {
           AlertDialog.Builder dlg = new AlertDialog.Builder(this);
           dlg.setTitle("Email ou senha incorretos");
           dlg.setNeutralButton("OK", null);
           AlertDialog dialog = dlg.create();
           dialog.show();

       }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }





}
*/