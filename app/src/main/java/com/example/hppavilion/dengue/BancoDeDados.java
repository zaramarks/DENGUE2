package com.example.hppavilion.dengue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

public class BancoDeDados extends SQLiteOpenHelper {
    private static final String NOME_BD = "teste";
    public static final String TABLE_NAME = "usuario";
    public static final String TABLE_NAME2 = "Casos";
    public static final String TABLE_NAME3 = "lingua";
    private static final int VERSAO_BD = 1;

    public static final String COL_1 = "id";
    public static final String COL_2 = "nome";
    public static final String COL_3 = "sobrenome";
    public static final String COL_4 = "email";
    public static final String COL_5 = "senha";
    public static final String COL_6 = "endereco";
    public static final String COL_7 = "lat";
    public static final String COL_8 = "lng";
    public static final String COL_9 = "tipo";

    private static  final String TABLE_CREATE = "create table usuario (id integer primary key  not null , "+" nome TEXT NOT NULL, sobrenome TEXT NOT NULL, email TEXT NOT NULL, senha TEXT NOT NULL, endereco TEXT NOT NULL, lat REAL, lng REAL, tipo TEXT NOT NULL );";

    private SQLiteDatabase bd;
    public static final String COL_ID = "Pid";
    public static final String COL_NOME = "Pnome";
    public static final String COL_ENDERECO = "Pendereco";
    public static final String COL_DOENCA = "Pdoenca";
    public static final String COL_LAT = "Plat";
    public static final String COL_LNG = "Plng";

    private static  final String TABLE_CREATE2 = "create table Casos ( Pid integer primary key  , "+" Pnome TEXT , Pdoenca TEXT , Pendereco TEXT, Plat REAL, Plng REAL  );";

    private static final String COL_IDL = "idd";
    private static final String COL_LINGUA = "linguaa";
    private static final String COL_EMAIL = "emaill";
    private static final String COL_SENHA = "senhaa";
    private static  final String TABLE_CREATE3 = "create table lingua (idd integer primary key, "+"linguaa TEXT, emaill TEXT, senhaa TEXT);";

    public BancoDeDados(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
        // dbb=this.getWritableDatabase();

    }




    @Override
    public void onCreate(SQLiteDatabase bd) {
        //db.execSQL("create table usuario (_id integer primary key autoincrement, NOME text not null, SOBRENOME text not null, EMAIL text not null, SENHA text not null);");

       // bd.execSQL("create table "+TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT , "+ " nome TEXT NOT NULL, sobrenome TEXT NOT NULL, email TEXT NOT NULL, senha TEXT NOT NULL )");
         // bd.execSQL("create table "+TABLE_NAME+"("+COL_1+"INTEGER PRIMARY KEY AUTOINCREMENT , "+COL_2 +" TEXT NOT NULL, "+COL_3+ "sobrenome TEXT NOT NULL,"+COL_4+" email TEXT NOT NULL, "+COL_5+"senha TEXT NOT NULL )");
        bd.execSQL(TABLE_CREATE);
        bd.execSQL(TABLE_CREATE2);
        bd.execSQL(TABLE_CREATE3);
        this.bd= bd;

        Log.e("BDS", "Criada");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME +TABLE_NAME2 + TABLE_NAME3);
        onCreate(db);
    }

    //public void deletar(SQL usuario){
       // bd.delete(TABLE_NAME, COL_1 +" = "+usuario.getId(), null);
  //  }

    public void insertContact(SQL c) {
        bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from usuario";
        Cursor cursor = bd.rawQuery(query, null);
        int count =cursor.getCount();

        values.put(COL_1, count);
        values.put(COL_2, c.getNome());
        values.put(COL_3, c.getSobrenome());
        values.put(COL_4, c.getEmail());
        values.put(COL_5, c.getSenha());
        values.put(COL_6, c.getEndereço());
        values.put(COL_7, c.getLat());
        values.put(COL_8, c.getLng());
        values.put(COL_9, c.getTipo());
        bd.insert(TABLE_NAME, null, values);
        Log.e("BD1","Criou uma coluna");


    }
   public void insertContactt(SQL c) {
       bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

       String query = "select * from Casos";
        Cursor cursor = bd.rawQuery(query, null);
        int count =cursor.getCount();

        values.put(COL_ID, count);
        values.put(COL_NOME, c.getPnome());
        values.put(COL_DOENCA, c.getPdoenca());
        values.put(COL_ENDERECO, c.getPendereco());
        values.put(COL_LAT, c.getPlat());
        values.put(COL_LNG, c.getPlng());
        bd.insert(TABLE_NAME2, null, values);


    }

    public void insertContacttt(SQL c){
        bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from lingua";
        Cursor cursor = bd.rawQuery(query, null);

        int count =cursor.getCount();
        values.put(COL_IDL, count);
        values.put(COL_LINGUA, c.getLingua());
        values.put(COL_EMAIL, c.getemaill());
        values.put(COL_SENHA, c.getSenhaa());
        bd.insert(TABLE_NAME3, null, values);
    }

    public Cursor listarpersonas() {
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM casos");
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

   /* public Cursor display(){
        SQLiteDatabase sql = getReadableDatabase();
        Cursor res;
        res=sql.rawQuery()
    }*/
    }








