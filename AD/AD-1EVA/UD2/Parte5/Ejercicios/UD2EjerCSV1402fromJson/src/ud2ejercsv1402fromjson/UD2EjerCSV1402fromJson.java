
package ud2ejercsv1402fromjson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

public class UD2EjerCSV1402fromJson {

     public static void main(String[] args) throws FileNotFoundException, IOException {
        File fichero = new File("./datos/sistoperativos.json"); // Declarar fichero
        Reader objReader = new FileReader(fichero); //Crear el flujo de entrada
        JsonArray jsonArray;
        ArrayList<String> lista = new ArrayList<>();
        try (JsonReader jsonReader = Json.createReader(objReader)) {
            
            jsonArray = jsonReader.readArray();
            //Recorriendo con for:
            System.out.println("...Recorriendo con FOR...\n");
            for (int i = 0; i < jsonArray.size(); i++) {
                int id = jsonArray.get(i).asJsonObject().getInt("Id");
                String nombre = jsonArray.get(i).asJsonObject().getString("Nombre");
                lista.add(id + "");
                lista.add(nombre);
                Muestra(jsonArray.get(i).asJsonObject().getInt("Id"), jsonArray.get(i).asJsonObject().getString("Nombre"));
                
            }
            
            File f = new File("./datos/jasonACSV");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Id|Nombre\n");
            for (int i = 0; i < lista.size(); i++) {
                bw.write(lista.get(i));
                bw.write("|");
                i++;
                bw.write(lista.get(i));
                bw.write("\n");
            }
            bw.close();
            
            
        }
    }
    private static void Muestra(int id, String nombre) {
    
        //System.out.println("{Id:"+id+"," +" Nombre:"+nombre+"}");
        System.out.println("Id: " + id);
        System.out.println("Nombre: " + nombre + "\n");
    }

}
