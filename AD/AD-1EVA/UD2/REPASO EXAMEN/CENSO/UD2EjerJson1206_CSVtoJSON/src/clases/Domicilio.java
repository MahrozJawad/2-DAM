
package clases;

import java.io.Serializable;


public class Domicilio implements Serializable  {
    String calle;
    int numero;
    int cpostal;
    String poblacion;

    public Domicilio() {
    }

    public Domicilio(String calle, int numero, int cpostal, String poblacion) {
        this.calle = calle;
        this.numero = numero;
        this.cpostal = cpostal;
        this.poblacion = poblacion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCpostal() {
        return cpostal;
    }

    public void setCpostal(int cpostal) {
        this.cpostal = cpostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    @Override
    public String toString() {
        return "Domicilio{" + "calle=" + calle + ", numero=" + numero + ", cpostal=" + cpostal + ", poblacion=" + poblacion + '}';
    }
    
    
    
    
}
