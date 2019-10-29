/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud2ejer1101;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class PersonasHandler extends DefaultHandler {
    
    private final ArrayList<Persona> personas = new ArrayList();
    private Persona persona;
    private String buffer;
    
    public ArrayList<Persona> getPersonas() {
        return personas;
    }
    @Override
    public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
    switch(qName) {
        case "ListaPersonas":
            break;
        case "DatosPersona":
            break;
        case "nombre":
            persona = new Persona();
            personas.add(persona);
            break;
        case "email":
            break;
        case "edad":
            break;
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer = new String(ch,start,length); // Almacenamos en nuestro buffer
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName) {
        case "ListaPersonas":
            break;
        case "DatosPersona":
            break;
        case "nombre":
            // Almacenamos en objeto
            persona.setNombre(buffer);
            break;
        case "email":
            // Almacenamos en objeto
            persona.setEmail(buffer);
            break;
        case "edad":
            // Almacenamos en objeto
            persona.setEdad(Integer.parseInt(buffer));
            break;
        }
    }
}
