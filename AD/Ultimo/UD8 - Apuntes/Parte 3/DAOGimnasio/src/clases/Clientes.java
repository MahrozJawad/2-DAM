
package clases;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="cliente")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"dni", "nombre", "apellidos", "fecha_nac", "telefono"})
public class Clientes implements Serializable {
    @XmlElement     String dni;
    @XmlElement     String nombre;
    @XmlElement     String apellidos;
    @XmlElement     String fecha_nac;
    @XmlElement     String telefono;

    public Clientes() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Clientes{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_nac=" + fecha_nac + ", telefono=" + telefono + '}';
    }
    
    
}
