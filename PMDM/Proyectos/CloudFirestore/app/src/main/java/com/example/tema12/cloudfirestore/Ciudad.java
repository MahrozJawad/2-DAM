package com.example.tema12.cloudfirestore;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Ciudad implements Serializable, Parcelable {
    private String Imagen;
    private String nombre;
    private String pais;


    public Ciudad(String imagen, String nombre, String pais) {
        Imagen = imagen;
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "Imagen='" + Imagen + '\'' +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Imagen);
        dest.writeString(this.nombre);
        dest.writeString(this.pais);
    }

    protected Ciudad(Parcel in) {
        this.Imagen = in.readString();
        this.nombre = in.readString();
        this.pais = in.readString();
    }

    public static final Parcelable.Creator<Ciudad> CREATOR = new Parcelable.Creator<Ciudad>() {
        @Override
        public Ciudad createFromParcel(Parcel source) {
            return new Ciudad(source);
        }

        @Override
        public Ciudad[] newArray(int size) {
            return new Ciudad[size];
        }
    };
}
