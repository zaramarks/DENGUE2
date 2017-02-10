package com.example.hppavilion.dengue;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.content.pm.ProviderInfo;
import android.database.sqlite.SQLiteDatabase;

public class SQL {
    private SQLiteDatabase bd;

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String endereço;
    private String Pnome;
    private String Pdoenca;
    private String Pendereco;
    private String tipo;
    private long id, Pid;
    // private long lat, lng, Plat, Plng;
    private double lat, lng, Plat, Plng;

    private String lingua;
    private String emaill;
    private String senhaa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public long getPid() {
        return Pid;
    }

    public void setPid(long Pid) {
        this.Pid = Pid;
    }

    public String getPnome() {
        return Pnome;
    }

    public void setPnome(String Pnome) {
        this.Pnome = Pnome;
    }

    public String getPdoenca() {
        return Pdoenca;
    }

    public void setPdoenca(String Pdoenca) {
        this.Pdoenca = Pdoenca;
    }

    public String getPendereco() {
        return Pendereco;
    }

    public void setPendereco(String Pendereco) {
        this.Pendereco = Pendereco;
    }

    public double getPlat() {
        return Plat;
    }

    public void setPlat(double Plat) {
        this.Plat = Plat;
    }

    public double getPlng() {
        return Plng;
    }

    public void setPlng(double Plng) {
        this.Plng = Plng;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }


    public void setemaill(String emaill) {
        this.emaill = emaill;
    }

    public String getemaill() {
        return emaill;
    }


    public String getSenhaa() {
        return senhaa;
    }

    public void setSenhaa(String senhaa) {
        this.senhaa = senhaa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}