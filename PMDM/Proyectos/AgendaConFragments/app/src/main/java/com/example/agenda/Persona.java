package com.example.agenda;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private String imagenString;

    public Persona() {
    }

    public Persona(String nombre, String apellidos, String telefono, String correo, String imagen) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.imagenString = imagen;
    }

    public String getImagen() {
        return imagenString;
    }

    public void setImagen(String imagen) {
        this.imagenString = imagen;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.apellidos);
        dest.writeString(this.telefono);
        dest.writeString(this.correo);
        dest.writeString(this.imagenString);
    }

    protected Persona(Parcel in) {
        this.nombre = in.readString();
        this.apellidos = in.readString();
        this.telefono = in.readString();
        this.correo = in.readString();
        this.imagenString = in.readString();
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel source) {
            return new Persona(source);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };
}
