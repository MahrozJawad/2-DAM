package ud2ejerjson1201;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class UD2EjerJson1201 {

    public static void main(String[] args) throws FileNotFoundException {
        
        File fichero = new File("./datos/contenedores-frutas-50.json"); // Declarar fichero
        Reader objReader = new FileReader(fichero); //Crear el flujo de entrada
        JsonArray jsonArray;
        try (JsonReader jsonReader = Json.createReader(objReader)) {
            
            jsonArray = jsonReader.readArray();
            int i=0;
            for (JsonValue x : jsonArray) {
                JsonObject o = (JsonObject)x;
                if(o.asJsonObject().getString("pais").equals("FR"))
                {
                    System.out.printf("%s %3d","idcontenedor: ",o.asJsonObject().getInt("idContenedor"));
                    System.out.printf("%15s %15s","producto: ",o.asJsonObject().getString("producto"));
                    System.out.printf("%15s %2s","pais: ",o.asJsonObject().getString("pais"));
                    System.out.printf("%10s %2s","Ciudad: ",o.asJsonObject().getString("ciudad"));
                    System.out.println();
                }
                i++;
            }
        }
    }
}
