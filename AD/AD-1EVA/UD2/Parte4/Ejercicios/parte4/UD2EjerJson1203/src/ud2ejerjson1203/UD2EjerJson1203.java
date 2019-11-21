package ud2ejerjson1203;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class UD2EjerJson1203 {

    public static void main(String[] args) throws IOException {
        // Conexión sin proxy
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/42");
        URLConnection uc = url.openConnection();
        HttpURLConnection conn = (HttpURLConnection) uc;
// La conexión se va a realizar para poder enviar y recibir
// información en formato JSON
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-type", "application/json");
// Se va a realizar una petición con el método GET
        conn.setRequestMethod("GET");
// Ejecutar la conexión y obtener la respuesta
        Reader objReader = new InputStreamReader(conn.getInputStream());
        JsonObject jsonObj;
        try (JsonReader jsonReader = Json.createReader(objReader)) {
            jsonObj = jsonReader.readObject();
// Leemos el fichero completo en formato Object
        }
        //FOREACH
        System.out.println("...Recorriendo con for...\n*************************");
        jsonObj.forEach((key, value) -> {
            System.out.println("Clave: " + key);
            System.out.println("Tipo: " + value.getValueType());
            System.out.println("Valor: " + value);  
            System.out.println();
        });
    }

}
