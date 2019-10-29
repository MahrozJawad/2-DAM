
package ejercicio1;

import clases.Barrio;
import clases.Barrios;
import clases.Domicilio;
import clases.Restaurante;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class Ejercicio1 {


    public static void main(String[] args) throws FileNotFoundException, JAXBException {
      
        
        // ****************************
        // Cargar JSON en memoria
        // ****************************
        JsonReader jreader = Json.createReader(new FileInputStream("./datos/restaurantes.json"));
        JsonObject jO = jreader.readObject();
        jreader.close();
        
        // **************************************************************
        // Recorrer mostrando datos y guardando los barrios en una lista
        // **************************************************************

        // Lista para guardar los barrios
        ArrayList<String> listaBarrios = new ArrayList<>();

        JsonArray jA = jO.getJsonArray("restaurantes");
        for (JsonValue restValue : jA) {
            JsonObject rest = (JsonObject) restValue;
            
            // Codigo, Barrio y Nombre
            System.out.println(rest.getInt("codigo")+":"+rest.getString("barrio")+":"+rest.getString("nombre"));
            
            // Domicilio (calle, codigo_postal)
            System.out.println("    Domicilio--> ");
            System.out.println("        "+rest.getJsonObject("domicilio").getString("calle")+":"+rest.getJsonObject("domicilio").getString("codigo_postal"));
            
            // Puntuaciones
            System.out.println("    Puntuaciones--> ");
            for (JsonValue puntValue : rest.getJsonArray("revisiones")) {
                JsonObject punt = (JsonObject) puntValue;
                System.out.println("      "+punt.getString("categoria")+"="+punt.getJsonNumber("puntuacion"));
            }
            
            // Guardamos el barrio si no existe
            if (!listaBarrios.contains(rest.getString("barrio"))) {
                listaBarrios.add(rest.getString("barrio"));
            }
        }
        Collections.sort(listaBarrios);
        
        System.out.println(listaBarrios.toString());

        // ****************************
        // Recorrer y cargar objetos
        // ****************************
        
        // Barrios 
        Barrios oBarrios = new Barrios();

        for (String strBarrio : listaBarrios) {
            // Barrio 
            Barrio oBarrio = new Barrio();
            oBarrio.setNombre(strBarrio);
            
            // Recorrer y mostrar restaurantes
            for (JsonValue restValue : jA) {
                JsonObject rest = (JsonObject) restValue;

                // Solo procesamos si el restaurante es del barrio
                if (rest.getString("barrio").equals(strBarrio)) {

                    // Domicilio
                    Domicilio oDom = new Domicilio();
                    oDom.setCalle(rest.getJsonObject("domicilio").getString("calle"));
                    oDom.setCodigo_postal(rest.getJsonObject("domicilio").getString("codigo_postal"));

                    // Restaurante
                    Restaurante oRest = new Restaurante();
                    oRest.setCodigo(rest.getInt("codigo"));
                    oRest.setNombre(rest.getString("nombre"));            
                    oRest.setDomicilio(oDom); // Añadimos el domicilio

                    // Máxima puntuación
                    String categ="";
                    Double puntos=-1.0;
                    for (JsonValue puntValue : rest.getJsonArray("revisiones")) {
                        JsonObject punt = (JsonObject) puntValue;
                        if (puntos < punt.getJsonNumber("puntuacion").doubleValue() ) {
                            categ = punt.getString("categoria");
                            puntos = punt.getJsonNumber("puntuacion").doubleValue();
                        }
                    }        
                    oRest.setCategoria(categ);
                    oRest.setPuntuacion(puntos);

                    // Añadimos restaurante a la lista
                    oBarrio.addRestaurante(oRest);
                }
                
            }        
            
            // Añadimos el Barrio con sus restaurantes
            oBarrios.addBarrio(oBarrio);

        }
        
        
        

        // ****************************
        // Crear Archivo XML con JAXB
        // ****************************
        JAXBContext context = JAXBContext.newInstance(Barrios.class);
        Marshaller marshaller = context.createMarshaller();
        
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshaller.marshal(oBarrios, 
                new BufferedWriter(
                    new OutputStreamWriter(
                       new FileOutputStream("./datos/barrios.xml", false),
                       StandardCharsets.UTF_8)));        
        
    }
    
    
    
}
