package com.example.hppavilion.dengue.adapter;

/**
 * Created by zazah on 22/04/2017.
 */

public class ListaAdapter {

    String tipo;
    String endereco;
    String nome;
    Double lat, lng;

    public ListaAdapter(String tipo, String endereco, String nome, Double lat, Double lng) {
        this.tipo = tipo;
        this.endereco = endereco;
        this.nome = nome;
        this.lat = lat;
        this.lng = lng;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
