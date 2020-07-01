package com.example.crudfirebasefirestorestorage.model;

import java.io.Serializable;

public class VehicleModel implements Serializable {

    private String id;
    private String modelo;
    private String marca;
    private String numeroRuedas;
    private String categoria;
    private String urlFoto;
    private boolean isActivo;

    public VehicleModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumeroRuedas() {
        return numeroRuedas;
    }

    public void setNumeroRuedas(String numeroRuedas) {
        this.numeroRuedas = numeroRuedas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public boolean isActivo() {
        return isActivo;
    }

    public void setActivo(boolean activo) {
        isActivo = activo;
    }
}
