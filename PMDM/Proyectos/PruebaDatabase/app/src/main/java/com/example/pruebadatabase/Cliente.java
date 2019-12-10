package com.example.pruebadatabase;

import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {

    private String dni;
    private String nombre;
    private String apelllidos;

    public Cliente(String dni, String nombre, String apelllidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apelllidos = apelllidos;
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

    public String getApelllidos() {
        return apelllidos;
    }

    public void setApelllidos(String apelllidos) {
        this.apelllidos = apelllidos;
    }

    public  static ArrayList<Cliente> getClientes(Cursor cursor) {
        ArrayList<Cliente> clientes;
        Cliente cliente;
        cursor.moveToFirst();
        if(!cursor.isAfterLast()) {
            clientes = new ArrayList<Cliente>();
            while (!cursor.isAfterLast()) {
                cliente = new Cliente(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                clientes.add(cliente);
                cursor.moveToNext();
            }
            return clientes;
        }

        return null;
    }

}
