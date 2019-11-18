
package com.modelos;

public class Respuesta {
    private int idRespuesta;
    private int idEncuesta;
    private String textoRespuesta;
    private int numeroRespuestas;

    public Respuesta(int idRespuesta, int idEncuesta, String textoRespuesta, int numeroRespuestas) {
        this.idRespuesta = idRespuesta;
        this.idEncuesta = idEncuesta;
        this.textoRespuesta = textoRespuesta;
        this.numeroRespuestas = numeroRespuestas;
    }

    public Respuesta() {
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public int getNumeroRespuestas() {
        return numeroRespuestas;
    }

    public void setNumeroRespuestas(int numeroRespuestas) {
        this.numeroRespuestas = numeroRespuestas;
    }
    

    
}
