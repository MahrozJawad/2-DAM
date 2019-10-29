
package ud2json03fromstring; 

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class UD2Json03fromString {

    public static void main(String[] args) {
// Crear String con el contenido del Json
        String cadenaJson = "{"
                + "\"nombre\": \"Juan\","
                + "\"edad\": 30,"
                + "\"encargado\": false"
                + "}";
        System.out.println(cadenaJson);
        System.out.println();
// Obtener un JsonObject a partir del String
        JsonObject jsonPersona = getObjectFromString(cadenaJson);
// Mostrar contenido de cada campo
        System.out.println("Nombre: " + jsonPersona.getString("nombre"));
        System.out.println("Edad: " + jsonPersona.getInt("edad"));
        System.out.println("Encargado: " + jsonPersona.getBoolean("encargado"));
        System.out.println();
// Recorrer el JsonObject
        jsonPersona.forEach((key, value) -> {
            System.out.println("Clave: " + key);
            System.out.println("Tipo: " + value.getValueType());
            System.out.println("Valor: " + value);
            System.out.println();
        });
    }
// Función para leer un JsonObject desde un String

    public static JsonObject getObjectFromString(String jsonObjectStr) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        return object;
    }
}
