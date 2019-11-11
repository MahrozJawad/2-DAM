
package com.modelos;


public class Preguntas {
    private int id;
    private String textoPregunta;

    public Preguntas(int id, String textoPregunta) {
        this.id = id;
        this.textoPregunta = textoPregunta;
    }

    public Preguntas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }
    
    

}
