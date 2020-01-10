package com.dam.persona;

import java.io.Serializable;

public class Domicilio implements Serializable {

    String direccion;
    String cpostal;
    String poblacion;
    String provincia;

    public Domicilio(String direccion, String cpostal, String poblacion, String provincia) {
        this.direccion = direccion;
        this.cpostal = cpostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCpostal() {
        return cpostal;
    }

    public void setCpostal(String cpostal) {
        this.cpostal = cpostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Domicilio{" + "direccion=" + direccion + ", cpostal=" + cpostal + ", poblacion=" + poblacion + ", provincia=" + provincia + '}';
    }

    
    
}
