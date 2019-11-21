
package UD2EjerJson1205Media;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "alumnos")
@XmlAccessorType(XmlAccessType.FIELD)

public class Alumnos {

    @XmlElement(name = "alumno")
    ArrayList<Alumno> alumnosArray = new ArrayList<Alumno>();
    
    public Alumnos() {
        
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnosArray;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnosArray = alumnos;
    }
    
}

