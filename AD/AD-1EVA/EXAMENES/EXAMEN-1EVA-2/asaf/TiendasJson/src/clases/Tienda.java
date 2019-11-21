
package clases;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Tienda implements Serializable {
    private String idtienda;
    private String provincia;
    private String creada;
    private String encargado;
    private int trabajadores;
    private ArrayList<Emails> emails;

    public Tienda() {
    }

    public Tienda(String idtienda) {
        this.idtienda = idtienda;
    }

    public Tienda(String idtienda, String provincia, String creada, String encargado, int trabajadores) {
        this.idtienda = idtienda;
        this.provincia = provincia;
        this.creada = creada;
        this.encargado = encargado;
        this.trabajadores = trabajadores;
    }

    public String getIdtienda() {
        return idtienda;
    }

    public void setIdtienda(String idtienda) {
        this.idtienda = idtienda;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCreada() {
        return creada;
    }

    public void setCreada(String creada) {
        this.creada = creada;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public int getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(int trabajadores) {
        this.trabajadores = trabajadores;
    }

    public ArrayList<Emails> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Emails> emails) {
        this.emails = emails;
    }
    
    @Override
    public String toString() {
        return "Tienda{" + "idtienda=" + idtienda + ", provincia=" + provincia + ", creada=" + creada + ", encargado=" + encargado + ", trabajadores=" + trabajadores + '}';
    }
    
    
            
            
}
