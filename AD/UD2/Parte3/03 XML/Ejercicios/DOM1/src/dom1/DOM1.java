/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom1;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class DOM1 {
    public static void main(String[] args) {
        try {
            // P1 – Leer y cargar XML en objecto DOM
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dbFactory.setIgnoringComments(true);
            dbFactory.setIgnoringElementContentWhitespace(true);
            File inputFile = new File("./datos/Asignaturas.xml");
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            // normalize – elimina nodos vacíos, y une nodos de texto adyacentes
            // pero que estaban separados por intros
            // P2 – Recorrer DOM mostrando elementos
            System.out.println("Elemento base : " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("asignatura");
            System.out.println();
            System.out.println("Recorriendo asignaturas...");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Codigo: " + eElement.getAttribute("id"));
                    System.out.println("Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Ciclo: " + eElement.getElementsByTagName("cicloFormativo").item(0).getTextContent());
                    System.out.println("Curso: " + eElement.getElementsByTagName("curso").item(0).getTextContent());
                    System.out.println("Profesor: " + eElement.getElementsByTagName("profesor").item(0).getTextContent());
                    System.out.println();
                } //if
            } //for
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
