package com.example.ejerciciopropleeryescribirxml;

public class Datos {

    String nombre;
    int puntos;
    boolean mujer;

    public Datos(String nombre, int puntos, boolean mujer) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.mujer = mujer;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public boolean isMujer() {
        return mujer;
    }

    public void setMujer(boolean mujer) {
        this.mujer = mujer;
    }
}
