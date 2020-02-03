
package Clases;

import java.io.Serializable;
import java.text.Collator;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="apirest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"nombre","kg"})
public class Frutas implements Serializable {
    @XmlElement(name="nombre") String nombre;
    @XmlElement(name="kg") int kg;
    public Frutas() {
    }

    public Frutas(String nombre) {
        this.nombre = nombre;
    }
    

    public String getFruta() {
        return nombre;
    }

    public void setFruta(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Frutas{" + "nombre=" + nombre + ", kg=" + kg + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Frutas other = (Frutas) obj;
        if (this.kg != other.kg) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }
    
    
}
