
package ud2ejer1107;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import javafx.print.Collation;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class UD2Ejer1107 {
    public static void main(String[] args) {
        try {
            // P1 – Leer y cargar XML en objecto DOM
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dbFactory.setIgnoringComments(true);
            dbFactory.setIgnoringElementContentWhitespace(true);
            File inputFile = new File("./datos/personas.xml");
            org.w3c.dom.Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            // normalize – elimina nodos vacíos, y une nodos de texto adyacentes
            // pero que estaban separados por intros
            // P2 – Recorrer DOM mostrando elementos
            System.out.println("Elemento base : " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("DatosPersona");
            System.out.println();
            
            System.out.println("Guardando personas...");
            File fichero = new File("./datos/personas.csv");
            PrintWriter pw = new PrintWriter(fichero);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if(temp == 0) {
                        pw.write("\"nombre\",\"email\",\"edad\"" + "\n");
                    }
                    pw.write("\"");
                    pw.write(eElement.getElementsByTagName("nombre").item(0).getTextContent()+"\",");
                    pw.write("\""+eElement.getElementsByTagName("email").item(0).getTextContent()+"\",");
                    pw.write("\""+eElement.getElementsByTagName("edad").item(0).getTextContent()+"\"");
                    pw.write("\n");
                } //if
            } //for
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
