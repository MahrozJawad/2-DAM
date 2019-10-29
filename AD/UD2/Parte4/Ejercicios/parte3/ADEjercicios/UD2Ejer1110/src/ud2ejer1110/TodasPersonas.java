/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud2ejer1110;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "ListaPersonas")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"personas"})
public class TodasPersonas {

    @XmlElement(name = "DatosPersona")
    private ArrayList<Persona> personas = new ArrayList<>();
// **********************************
// Constructor

    public TodasPersonas() {
    }
// getters y setters


    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> persona) {
        this.personas = persona;
    }
}
