
package clases;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


public class Barrio implements Serializable {

    String nombre;
	
 
    ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();

    public Barrio() {
    }

    public Barrio(String nombre) {
        this.nombre = nombre;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }

    public void setListaRestaurantes(ArrayList<Restaurante> listaRestaurantes) {
        this.listaRestaurantes = listaRestaurantes;
    }

    @Override
    public String toString() {
        return "Barrio{" + "nombre=" + nombre + ", listaRestaurantes=" + listaRestaurantes + '}';
    }
    
    
    
    
    
    public void addRestaurante(Restaurante r){
        listaRestaurantes.add(r);
    }

} 