
package ud2ejerjson1202;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class UD2EjerJson1202 {

    public static void main(String[] args) throws FileNotFoundException {
        File fichero = new File("./datos/sistoperativos.json"); // Declarar fichero
        Reader objReader = new FileReader(fichero); //Crear el flujo de entrada
        JsonArray jsonArray;
        try (JsonReader jsonReader = Json.createReader(objReader)) {
            
            jsonArray = jsonReader.readArray();
            //Recorriendo con for:
            System.out.println("...Recorriendo con for...\n");
            for (int i = 0; i < jsonArray.size(); i++) {
                
                Muestra(jsonArray.get(i).asJsonObject().getInt("Id"), jsonArray.get(i).asJsonObject().getString("Nombre"));
                
            }
            
            
            //recorriendo con forEach:
            System.out.println("\n...Recorriendo con Foreach...\n");
            for (JsonValue x : jsonArray) {
                JsonObject objeto = (JsonObject)x;
                Muestra(objeto.asJsonObject().getInt("Id"),objeto.asJsonObject().getString("Nombre"));
            }
            
            //Recorriendo con iterator:
            System.out.println("\n...Recorriendo con iterator...\n");
            Iterator it = jsonArray.iterator();
            while (it.hasNext()) {
                JsonObject objeto = (JsonObject)it.next();
                Muestra(objeto.getInt("Id"), objeto.getString("Nombre"));
            }
        }
    }
    private static void Muestra(int id, String nombre) {
    
        System.out.println("{Id:"+id+"," +" Nombre:"+nombre+"}");
        System.out.println("Id: " + id);
        System.out.println("Nombre: " + nombre);
    }
}
