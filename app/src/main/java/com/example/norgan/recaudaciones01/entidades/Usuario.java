package com.example.norgan.recaudaciones01.entidades;

/**
 * Created by norgan on 14/04/2018.
 */

public class Usuario {


    private  Integer documento;
    private  Integer monto;
    private String origen;
    private  String lugar;


    public Usuario() {
    }

    public Usuario(Integer documento, Integer monto, String origen, String lugar) {
        this.documento = documento;
        this.monto = monto;
        this.origen = origen;
        this.lugar = lugar;
    }


    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
