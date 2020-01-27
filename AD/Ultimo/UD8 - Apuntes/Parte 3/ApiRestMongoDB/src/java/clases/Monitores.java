
package clases;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="monitor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id_monitor", "nombre", "apellidos"})
public class Monitores implements Serializable {
    @XmlElement String id_monitor;
    @XmlElement String nombre;
    @XmlElement String apellidos;

    public Monitores() {
    }

    public Monitores(String id_monitor, String nombre, String apellidos) {
        this.id_monitor = id_monitor;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getId_monitor() {
        return id_monitor;
    }

    public void setId_monitor(String id_monitor) {
        this.id_monitor = id_monitor;
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

    @Override
    public String toString() {
        return "Monitores{" + "id_monitor=" + id_monitor + ", nombre=" + nombre + ", apellidos=" + apellidos + '}';
    }
    
    
}
