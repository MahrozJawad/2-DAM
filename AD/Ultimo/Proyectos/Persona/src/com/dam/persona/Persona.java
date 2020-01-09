
package com.dam.persona;

import java.io.Serializable;


public class Persona implements Serializable {
    int dni;
    String nombre;
    String apellidos;
    String telefono;
    Domicilio domicilio;

    // CONSTRUCTORES

    public Persona() {
    }

    public Persona(int dni) {
        this.dni = dni;
    }

    public Persona(int dni, String nombre, String apellidos, String telefono, Domicilio domicilio) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.domicilio = domicilio;
    }
    
    // GETTERES Y SETTERS 

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    
    //TOSTRING

    @Override
    public String toString() {
        return "Persona{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", domicilio=" + domicilio + '}';
    }
    
    
}
