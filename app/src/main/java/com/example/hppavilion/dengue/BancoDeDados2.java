/*package com.example.hppavilion.dengue.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hppavilion.dengue.SQL;


public class BancoDeDados2 extends SQLiteOpenHelper {
    private static final String NOME_BD2 = "teste";
    public static final String TABLE_NAME2 = "casos";
    private static final int VERSAO_BD2 = 1;

    public static final String COL_ID = "_id";
    public static final String COL_NOME = "Pnome";
    public static final String COL_ENDERECO = "Pendereco";
    public static final String COL_DOENCA = "Pdoenca";
    private static final String TAG = "DBAdapter";
    private static  final String TABLE_CREATE2 = "create table casos (_id integer primary key autoincrement not null , "+" Pnome TEXT NOT NULL, Pdoenca TEXT NOT NULL, Pendereco TEXT NOT NULL );";

    public SQLiteDatabase bdd;

    public BancoDeDados2(Context context) {
        super(context, NOME_BD2, null, VERSAO_BD2);
        // dbb=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase bdd) {
        bdd.execSQL(TABLE_CREATE2);
        this.bdd= bdd;
        Log.e("BD2", "Criada");

    }

    @Override
    public void onUpgrade(SQLiteDatabase dbb, int oldVersion, int newVersion) {
        dbb.execSQL("drop table if exists "+TABLE_NAME2);
        onCreate(dbb);
    }


    //public void deletar(SQL usuario){
       // bdd.delete(TABLE_NAME, COL_1 +" = "+usuario.getId(), null);
   // }

    public void insertContactt(SQL c) {
        bdd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

      /*  String query = "select * from casos";
        Cursor cursor = bdd.rawQuery(query, null);
        int count =cursor.getCount();

        values.put(COL_ID, count);
        values.put(COL_NOME, c.getPnome());
        values.put(COL_DOENCA, c.getPdoenca());
        values.put(COL_ENDERECO, c.getPendereco());
        bdd.insert(TABLE_NAME2, null, values);


    }


}
*/