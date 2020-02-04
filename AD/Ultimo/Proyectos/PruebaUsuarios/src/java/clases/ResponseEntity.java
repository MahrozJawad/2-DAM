
package clases;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"status","mensaje"})
public class ResponseEntity implements Serializable {
    @XmlElement int status;
    @XmlElement String mensaje;

    public ResponseEntity() {
    }

    public ResponseEntity(int status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "ResponseClass{" + "status=" + status + ", mensaje=" + mensaje + '}';
    }

    
    
    
}
