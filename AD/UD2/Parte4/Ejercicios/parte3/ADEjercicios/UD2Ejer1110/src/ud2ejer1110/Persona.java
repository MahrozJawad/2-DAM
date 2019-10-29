
package ud2ejer1110;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;


@XmlRootElement(name="DatosPersona")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"nombre","email","edad"})
public class Persona {
    @XmlElement(name="nombre")
    private String nombre;
    @XmlElement(name="email")
    private String email;
    @XmlElement(name="autor")
    private String edad;

    public Persona() {
    }

    public Persona(String nombre, String email, String edad) {
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    } 
}
