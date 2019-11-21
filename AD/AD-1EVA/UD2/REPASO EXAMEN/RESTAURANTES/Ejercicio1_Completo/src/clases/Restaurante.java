
package clases;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="restaurante")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"codigo","nombre","domicilio","categoria","puntuacion"})
public class Restaurante implements Serializable {
    @XmlAttribute
    int codigo;
	
    @XmlElement
    String nombre;
	
    @XmlElement
    Domicilio domicilio;
	
    @XmlElement
    String categoria;
	
    @XmlElement
    double puntuacion;

    public Restaurante() {
    }

    public Restaurante(int codigo, String nombre, Domicilio domicilio, String categoria, double puntuacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.categoria = categoria;
        this.puntuacion = puntuacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Restaurante{" + "codigo=" + codigo + ", nombre=" + nombre + ", domicilio=" + domicilio + ", categoria=" + categoria + ", puntuacion=" + puntuacion + '}';
    }
    
  
}
