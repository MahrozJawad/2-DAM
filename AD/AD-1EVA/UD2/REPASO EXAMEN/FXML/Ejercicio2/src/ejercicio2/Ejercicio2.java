
package ejercicio2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Ejercicio2 {


    public static void main(String[] args) {

        try { 
            File inputFile = new File("./datos/input.xml"); 
			
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            dbFactory.setIgnoringComments(true);
            dbFactory.setIgnoringElementContentWhitespace(true);         
            Document doc = dBuilder.parse(inputFile); 
            doc.getDocumentElement().normalize(); 
			
            System.out.println("Elemento base : " + doc.getDocumentElement().getNodeName()); // AnchorPane
            System.out.println("------------------------"); 
            
            // ************************
            // Recorrer desde TableView
            // ************************
            NodeList nList = doc.getElementsByTagName("TableView"); 
            for (int tabla = 0; tabla < nList.getLength(); tabla++) { 
                Node nNodeTabla = nList.item(tabla); 
                if (nNodeTabla.getNodeType() == Node.ELEMENT_NODE) { 
                    Element elemTabla = (Element) nNodeTabla; 
                    System.out.print("  Tabla: " + elemTabla.getAttribute("fx:id"));
                    System.out.println(); 
                    
                    NodeList nCampos = ((Element) nNodeTabla).getElementsByTagName("columns").item(0).getChildNodes();

                    for (int campo = 0; campo < nCampos.getLength(); campo++) { 
                        Node nNodeCampo = nCampos.item(campo); 
                        if (nNodeCampo.getNodeType() == Node.ELEMENT_NODE) { 
                            Element elemCampo = (Element) nNodeCampo; 
                            System.out.print("    Columna: " + elemCampo.getAttribute("fx:id"));
                            System.out.print("    Anchura: " + elemCampo.getAttribute("prefWidth")); 
                            System.out.println(); 
                        }
                    }
                } 
            } 
            System.out.println("------------------------"); 
            
            // ************************
            // Recorrer desde TableColumn
            // ************************
            nList = doc.getElementsByTagName("TableColumn"); 
            for (int tabla = 0; tabla < nList.getLength(); tabla++) { 
                Node nNodeCampo = nList.item(tabla); 
                if (nNodeCampo.getNodeType() == Node.ELEMENT_NODE) { 
                    Element elemCampo = (Element) nNodeCampo; 
                    System.out.print("  Tabla..: " + ((Element) elemCampo.getParentNode().getParentNode()).getAttribute("fx:id"));
                    System.out.print("  Columna: " + elemCampo.getAttribute("fx:id"));
                    System.out.print("  Anchura: " + elemCampo.getAttribute("prefWidth")); 
                    System.out.println(); 
                } 
            }             
            System.out.println("------------------------"); 

            // ************************
            // Recorrer creando Json
            // ************************
            JsonObjectBuilder jOColumnas = Json.createObjectBuilder();
            JsonArrayBuilder jAColumnas = Json.createArrayBuilder();
            nList = doc.getElementsByTagName("TableColumn"); 
            for (int tabla = 0; tabla < nList.getLength(); tabla++) { 
                Node nNodeCampo = nList.item(tabla); 
                if (nNodeCampo.getNodeType() == Node.ELEMENT_NODE) { 
                    Element elemCampo = (Element) nNodeCampo; 
                    
                    // Columna
                    JsonObjectBuilder jObCol = Json.createObjectBuilder();
                    jObCol.add("columna",   elemCampo.getAttribute("fx:id"));
                    
                    // Propiedades
                    JsonObjectBuilder jsonObProp = Json.createObjectBuilder();
                    jsonObProp.add("tabla",     ((Element) elemCampo.getParentNode().getParentNode()).getAttribute("fx:id"));
                    jsonObProp.add("ancho",   elemCampo.getAttribute("prefWidth"));
                    jObCol.add("propiedades",  jsonObProp); 
                    
                    jAColumnas.add(jObCol);
                } 
            }             
            jOColumnas.add("columnas",  jAColumnas); 
            JsonObject jMain = jOColumnas.build();  
            
            // *******************
            // Crear archivo JSON
            // *******************
            JsonWriterFactory jsonFactory = Json.createWriterFactory(
                    Collections.singletonMap(JsonGenerator.PRETTY_PRINTING,true)
                );
            JsonWriter jsonW = jsonFactory.createWriter(  new FileOutputStream("./datos/salida.json")    );
            jsonW.writeObject(jMain);
            jsonW.close();            
            
            
	} catch (IOException | ParserConfigurationException | SAXException e) { 
            e.printStackTrace(); 
	}


        
    }
    
}
