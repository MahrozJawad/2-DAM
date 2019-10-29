
package ud2ejer1106;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javafx.print.Collation;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class UD2Ejer1106 {
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
            ArrayList<String> nombres = new ArrayList<>();
            
            System.out.println("Recorriendo personas...");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    nombres.add(eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    //System.out.println("Email: " + eElement.getElementsByTagName("email").item(0).getTextContent());
                    //System.out.println("Edad: " + eElement.getElementsByTagName("edad").item(0).getTextContent());
                    //System.out.println();
                } //if
            } //for
            
            Collections.sort(nombres);
            
            nombres.forEach((x) -> {
                System.out.println(x);
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
