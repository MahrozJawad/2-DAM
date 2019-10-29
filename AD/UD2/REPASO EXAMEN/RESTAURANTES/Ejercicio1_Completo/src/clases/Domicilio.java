
package clases;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="domicilio")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"calle","codigo_postal"})
public class Domicilio implements Serializable {
    @XmlElement
    String calle;
	
    @XmlElement
    String codigo_postal;

    public Domicilio() {
    }

    public Domicilio(String calle, String codigo_postal) {
        this.calle = calle;
        this.codigo_postal = codigo_postal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    @Override
    public String toString() {
        return "Domicilio{" + "calle=" + calle + ", codigo_postal=" + codigo_postal + '}';
    }

    
    
    
    
}