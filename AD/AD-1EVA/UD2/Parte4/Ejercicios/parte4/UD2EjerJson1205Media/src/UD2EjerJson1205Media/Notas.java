
package UD2EjerJson1205Media;

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
    
    private int numNotas;
    private float notaMedia;

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

    public int getNumNotas() {
        return this.nota.size();
    }

    public void setNumNotas(int numNotas) {
        this.numNotas = numNotas;
    }

    public float getNotaMedia() {
        float laNota = 0;
        numNotas = getNumNotas();
        for (int i = 0; i < numNotas; i++) {
            laNota +=nota.get(i);
        }
        notaMedia = laNota/numNotas;
        return notaMedia;
    }

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }

}
