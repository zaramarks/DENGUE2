package com.example.hppavilion.dengue;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;



import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by zazah on 08/06/2016.
 */
public class Linguaa extends AppCompatActivity {

    public Spinner sl;
    public static ArrayAdapter<String> LanguageOpcoes;
    public static String Lingua, LINGUA;
    public Button login, cadastroo;
    String cursorr;
    int a =0, b=0, c=0;

    BancoDeDados helper = new BancoDeDados(this);

    private CheckBox checkBoxA, checkBoxB, checkBoxC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linguaa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login = (Button) findViewById(R.id.button5);
        cadastroo = (Button) findViewById(R.id.button6);

        BancoDeDados bd = new BancoDeDados(this);
        SQLiteDatabase banco = bd.getReadableDatabase();
        Cursor cursor = banco.query("lingua", null, null, null, null, null, null);

        checkBoxA = (CheckBox) findViewById(R.id.checkBoxA);
        checkBoxB = (CheckBox) findViewById(R.id.checkBoxB);
        checkBoxC = (CheckBox) findViewById(R.id.checkBoxC);



    }
    public void LINGUA(View v) {


        if (a==1){
            Lingua = "Português";

        SQL c = new SQL();
        c.setLingua(Lingua);
        helper.insertContacttt(c);
            Intent a = new Intent(Linguaa.this, MenuDeEntrada.class);
            startActivity(a);
    }else if (b==1){
            Lingua = "Español";
            SQL c = new SQL();
            c.setLingua(Lingua);
            helper.insertContacttt(c);
            Intent a = new Intent(Linguaa.this, MenuDeEntrada.class);
            startActivity(a);
        }else if (c==1){
            Lingua = "English";
            SQL c = new SQL();
            c.setLingua(Lingua);
            helper.insertContacttt(c);
            Intent a = new Intent(Linguaa.this, MenuDeEntrada.class);
            startActivity(a);
        }else if (a==0 && b==0 && c==0){
            Toast.makeText(this, "Escolha uma lingua", Toast.LENGTH_SHORT).show();
        }



    }
    public void onCheckboxClicked(View view) {

        switch(view.getId()) {

            case R.id.checkBoxA:

                checkBoxB.setChecked(false);
                checkBoxC.setChecked(false);

                    a = 1;
                    b=0;
                    c=0;
                break;

            case R.id.checkBoxB:

                checkBoxC.setChecked(false);
                checkBoxA.setChecked(false);
                a = 0;
                b=1;
                c=0;
                break;

            case R.id.checkBoxC:

                checkBoxA.setChecked(false);
                checkBoxB.setChecked(false);
                a = 0;
                b=0;
                c=1;
                break;
        }
    }
      /*
        BancoDeDados bd = new BancoDeDados(this);
        SQLiteDatabase banco = bd.getReadableDatabase();
        Cursor cursor = banco.query("lingua", null, null, null, null, null, null);
       if (cursor.moveToFirst()) {
            do {
                cursorr = cursor.getColumnIndex("idd");

            }while (cursor.moveToNext());
        }

        if (cursorr >= 0){

            String delete = "DELETE FROM lingua";
            banco.rawQuery(delete, null);

            Lingua = (String) sl.getSelectedItem();
            SQL c = new SQL();
            c.setLingua(Lingua);
            helper.insertContacttt(c);

            Toast.makeText(this, "IF!", Toast.LENGTH_SHORT).show();
        }else  {
            Lingua = (String) sl.getSelectedItem();
            Toast.makeText(this, "ELSE", Toast.LENGTH_SHORT).show();

            SQL c = new SQL();
            c.setLingua(Lingua);
            helper.insertContacttt(c);
        }

        if (cursor.moveToFirst()) {
            do {
                LINGUA = cursor.getString(cursor.getColumnIndex("linguaa"));
                switch (LINGUA) {
                    case "Português":
                        cadastroo.setText("Cadastre-se");

                        // Toast.makeText(this, "Lingua alterada!", Toast.LENGTH_SHORT).show();


                        break;
                    case "Español":
                        cadastroo.setText("Registrar");
                        // Toast.makeText(this, "El lenguaje cambia!", Toast.LENGTH_SHORT).show();
                        break;

                    case "English":
                        cadastroo.setText("Register");
                        //Toast.makeText(this, "Language changed!", Toast.LENGTH_SHORT).show();

                        break;

                    default:
                        cadastroo.setText("Cadastree-se");


                }
            }while (cursor.moveToNext());
        }*/


}
