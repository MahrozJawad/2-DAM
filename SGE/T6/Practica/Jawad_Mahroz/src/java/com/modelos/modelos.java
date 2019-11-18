
package com.modelos;


public class modelos {

    private int idModelo;

    public modelos() {
    }
    private int idMarca;
    private String nombreModelo;
    private int stock;

    public modelos(int idModelo, int idMarca, String nombreModelo, int stock) {
        this.idModelo = idModelo;
        this.idMarca = idMarca;
        this.nombreModelo = nombreModelo;
        this.stock = stock;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
}
