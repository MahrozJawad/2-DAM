/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud2json02;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

public class UD2JSON02 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Objeto> ListaObjetos = new ArrayList<>();
        Objeto o1 = new Objeto(1,"Windows");
        Objeto o2 = new Objeto(2,"Linux");
        ListaObjetos.add(o1);
        ListaObjetos.add(o2);
        System.out.println(ListaObjetos.toString());
        
        JsonArrayBuilder jsonArrayB = Json.createArrayBuilder();
        for (Objeto obj : ListaObjetos) {
        JsonObjectBuilder jsonOB = Json.createObjectBuilder();
        jsonOB.add("Id", obj.idObjeto);
        jsonOB.add("Nombre", obj.nomObjeto);
        jsonArrayB.add(jsonOB);
        }
        JsonArray arrayJ = jsonArrayB.build();
        
        JsonWriterFactory jsonFactory = Json.createWriterFactory(
        Collections.singletonMap(JsonGenerator.PRETTY_PRINTING,true)
        );
        JsonWriter jsonW =
        jsonFactory.createWriter(
            new FileOutputStream("./datos/salida.json")
        );
        jsonW.writeArray(arrayJ);
        jsonW.close();
    }
    
    
}
