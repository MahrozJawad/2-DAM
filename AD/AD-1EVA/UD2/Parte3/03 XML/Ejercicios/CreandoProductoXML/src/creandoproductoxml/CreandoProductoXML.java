/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creandoproductoxml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author alumno
 */
public class CreandoProductoXML {

    public static void main(String[] args) throws ParserConfigurationException, TransformerConfigurationException, IOException, TransformerException {
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        dbFactory.setIgnoringComments(true);
        dbFactory.setIgnoringElementContentWhitespace(true);
        Document document = builder.newDocument();
        
        Element productos = document.createElement("productos");
        document.appendChild(productos);
        
        
        Element producto = document.createElement("producto");
        productos.appendChild(producto);
        producto.setAttribute("codigo", "1");
        Element nombre = document.createElement("nombre");
        nombre.appendChild(document.createTextNode("Teclado"));
        producto.appendChild(nombre);
        
        //Transformando
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformer = factoria.newTransformer();
        // Preparar fuente de "Object factory"
        Source source = new DOMSource(document);
        // Preparar salida para el archivo XML
        File file = new File("./datos/productos.xml");
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
