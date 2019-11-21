
package ud2ejerjson1204fromxml;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "notas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Notas {

    @XmlElement(name = "nota")
    private ArrayList<Float> nota = new ArrayList<>();

    public Notas() {
        
    }

    @Override
    public String toString() {
        return "Notas{" + "nota=" + nota + '}';
    }

    public ArrayList<Float> getNota() {
        return nota;
    }

    public void setNota(ArrayList<Float> nota) {
        this.nota = nota;
    }

}
