
package ud2ejercsv1401toxml;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="frutas")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = "contenedores")
public class FrutasJAXB {
    
    @XmlElement(name = "contenedor")
    private ArrayList<Contenedor> contenedores = new ArrayList<Contenedor>();

    public FrutasJAXB() {
    }

    public ArrayList<Contenedor> getContenedores() {
        return contenedores;
    }

    public void setContenedores(ArrayList<Contenedor> contenedores) {
        this.contenedores = contenedores;
    }
    
    
}
