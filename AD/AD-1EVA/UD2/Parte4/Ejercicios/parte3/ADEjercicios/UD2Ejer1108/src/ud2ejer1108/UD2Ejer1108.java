
package ud2ejer1108;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UD2Ejer1108 {

    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
     
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        dbFactory.setIgnoringComments(true);
        dbFactory.setIgnoringElementContentWhitespace(true);
        Document document = builder.newDocument();
        
        //Leer el fichero personas.csv
        BufferedReader bf = new BufferedReader(new FileReader("E:\\2DAM\\AD\\UD2\\Parte3\\03 XML\\Ejercicios\\UD2Ejer1108\\datos\\personas.csv"));
        String linea ="";
        ArrayList<String> al = new ArrayList<>();
        //leo la primera linea para que no me genere el arbol.
        bf.readLine();
        while((linea = bf.readLine()) != null)
        {
            linea = linea.replace("\"", "");
            String[] separado = linea.split(",");
            al.add(separado[0]);
            al.add(separado[1]);
            al.add(separado[2]);
        }
        bf.close();
        
        Element listaPersonas = document.createElement("ListaPersonas");
        document.appendChild(listaPersonas);

        for (int i = 0; i < al.size(); i++) {
            Element datosPersona = document.createElement("DatosPersona");
            listaPersonas.appendChild(datosPersona);
            //datosPersona.setAttribute("codigo", "1");
            Element nombre = document.createElement("nombre");
            datosPersona.appendChild(nombre);
            nombre.appendChild(document.createTextNode(al.get(i)));
            
            Element email = document.createElement("email");
            datosPersona.appendChild(email);
            email.appendChild(document.createTextNode(al.get(++i)));
            
            Element edad = document.createElement("edad");
            datosPersona.appendChild(edad);
            edad.appendChild(document.createTextNode(al.get(++i)));
        }
        

        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformer = factoria.newTransformer();
        // Preparar fuente de "Object factory"
        Source source = new DOMSource(document);
        // Preparar salida para el archivo csv
        File file = new File("E:\\2DAM\\AD\\UD2\\Parte3\\03 XML\\Ejercicios\\UD2Ejer1108\\datos\\personas.xml");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        Result result = new StreamResult(pw);
        // Para tabulaciones
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        // Realizar transformación: "Object Factory" to "Archivo Serializado (XML")
        transformer.transform(source, result);

        
    }
    
}
