package com.example.examen;

import android.os.Parcel;
import android.os.Parcelable;

public class Reparacion implements Parcelable {
    String codigo, tecnico, fchEntrada, fchSolucion, comentarios;

    public Reparacion() {
    }

    public Reparacion(String codigo, String tecnico, String fchEntrada, String fchSolucion, String comentarios ) {
        this.codigo = codigo;
        this.comentarios = comentarios;
        this.fchSolucion = fchSolucion;
        this.fchEntrada = fchEntrada;
        this.tecnico = tecnico;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFchEntrada(String fchEntrada) {
        this.fchEntrada = fchEntrada;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public void setFchSolucion(String fchSolucion) {
        this.fchSolucion = fchSolucion;
    }

    public String getTecnico() {
        return tecnico;
    }

    public String getFchEntrada() {
        return fchEntrada;
    }

    public String getFchSolucion() {
        return fchSolucion;
    }

    public String getComentarios() {
        return comentarios;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.codigo);
        dest.writeString(this.tecnico);
        dest.writeString(this.fchEntrada);
        dest.writeString(this.fchSolucion);
        dest.writeString(this.comentarios);
    }

    protected Reparacion(Parcel in) {
        this.codigo = in.readString();
        this.tecnico = in.readString();
        this.fchEntrada = in.readString();
        this.fchSolucion = in.readString();
        this.comentarios = in.readString();
    }

    public static final Parcelable.Creator<Reparacion> CREATOR = new Parcelable.Creator<Reparacion>() {
        @Override
        public Reparacion createFromParcel(Parcel source) {
            return new Reparacion(source);
        }

        @Override
        public Reparacion[] newArray(int size) {
            return new Reparacion[size];
        }
    };
}