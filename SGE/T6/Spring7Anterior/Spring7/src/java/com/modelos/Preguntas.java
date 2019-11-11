
package com.modelos;


public class Preguntas {
    private int id;
    private String stringPregunta;

    public Preguntas(int id, String stringPregunta) {
        this.id = id;
        this.stringPregunta = stringPregunta;
    }

    public Preguntas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringPregunta() {
        return stringPregunta;
    }

    public void setStringPregunta(String stringPregunta) {
        this.stringPregunta = stringPregunta;
    }
}
