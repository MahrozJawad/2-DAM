package com.dam.bibliotecah;

public class Libros {

    private int id;
    private String titulo;
    private String autor;

    public Libros(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libros{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + '}';
    }
    
}
