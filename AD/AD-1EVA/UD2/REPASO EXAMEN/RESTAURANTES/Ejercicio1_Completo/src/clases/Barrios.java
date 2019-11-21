
package clases;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="barrios")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"listaBarrios"})
public class Barrios implements Serializable {
    @XmlElement(name="barrio")
    ArrayList<Barrio> listaBarrios = new ArrayList<Barrio>();

    public Barrios() {
    }

    public ArrayList<Barrio> getListaBarrios() {
        return listaBarrios;
    }

    public void setListaBarrios(ArrayList<Barrio> listaBarrios) {
        this.listaBarrios = listaBarrios;
    }

    @Override
    public String toString() {
        return "Barrios{" + "listaBarrios=" + listaBarrios + '}';
    }
    
    
    
    public void addBarrio(Barrio b) {
        listaBarrios.add(b);
    }
}
