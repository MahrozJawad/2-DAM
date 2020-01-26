package com.dam.gimmongoclass;


import java.io.Serializable;


public class Cliente implements Serializable {
    String dni;
    String nombre;
    String apellidos;
    String fecha_nac;
    String telefono;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String apellidos, String fecha_nac, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nac = fecha_nac;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_nac=" + fecha_nac + ", telefono=" + telefono + '}';
    }
    
    
    
            
}
