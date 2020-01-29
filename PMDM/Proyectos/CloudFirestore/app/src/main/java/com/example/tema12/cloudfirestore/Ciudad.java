package com.example.tema12.cloudfirestore;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Ciudad implements Serializable, Parcelable {
    private String Imagen;
    private String Nombre;
    private String Pais;

    public Ciudad() {
    }

    public Ciudad(String imagen, String nombre, String pais) {
        Imagen = imagen;
        this.Nombre = nombre;
        this.Pais = pais;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        this.Pais = pais;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "Imagen='" + Imagen + '\'' +
                ", nombre='" + Nombre + '\'' +
                ", pais='" + Pais + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Imagen);
        dest.writeString(this.Nombre);
        dest.writeString(this.Pais);
    }

    protected Ciudad(Parcel in) {
        this.Imagen = in.readString();
        this.Nombre = in.readString();
        this.Pais = in.readString();
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
