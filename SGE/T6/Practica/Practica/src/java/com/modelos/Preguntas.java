
package com.modelos;


public class Preguntas {
    private int idEncuesta;
    private String textoPregunta;

    public Preguntas(int id, String textoPregunta) {
        this.idEncuesta = id;
        this.textoPregunta = textoPregunta;
    }

    public Preguntas() {
    }

    public int getId() {
        return idEncuesta;
    }

    public void setId(int id) {
        this.idEncuesta = id;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }
    
    

}
