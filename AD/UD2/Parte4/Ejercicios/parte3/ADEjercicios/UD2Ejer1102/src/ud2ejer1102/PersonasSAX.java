/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud2ejer1102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class PersonasSAX {
    public static void main(String[] args)throws ParserConfigurationException, SAXException, IOException {
        
        // P1 - Parsear el contenido del XML a objectos de la Clase Version
        SAXParserFactory saxParserfactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserfactory.newSAXParser();
        PersonasHandler handler = new PersonasHandler();
        File file = new File("./datos/personas.xml");
        saxParser.parse(file, handler); // Llama al Manejador
        
        // P2 - Mostrar en pantalla los objetos de la Clase Version
        ArrayList<Persona> personas = handler.getPersonas();
        
        System.out.println("Nombres;Email");
        for (int i = 0; i < personas.size(); i++) {
            System.out.println(personas.get(i).getNombre() + ";" + personas.get(i).getEmail());
        }
    
    }
}
