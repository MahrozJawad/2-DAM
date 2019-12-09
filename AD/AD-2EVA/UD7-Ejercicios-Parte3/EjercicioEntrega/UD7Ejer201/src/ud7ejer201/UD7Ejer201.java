package ud7ejer201;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class UD7Ejer201 {

    public static void main(String[] args) {
        // Log de MongoDB
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
// Crear cliente
        MongoClient mongoClient = new MongoClient("localhost", 27017);
// Seleccionar base de datos
        MongoDatabase database = mongoClient.getDatabase("instituto");

        MongoCollection<Document> collection = database.getCollection("profesores");
// Vacía la colección si existe
        collection.drop();
// Insertar los documentos
        System.out.println("Insertando documentos...");
// Creamos una lista de documentos
        ArrayList<Document> docs = new ArrayList<>();
        Profesor profes;
        profes = new Profesor("Pedro", "Álbarez", "mañana");
        docs.add(profes.toDoc());
        
        profes = new Profesor("Manuel", "Sánchez", "tarde");
        docs.add(profes.toDoc());
        
        profes = new Profesor("Laura", "González", "tarde");
        docs.add(profes.toDoc());
        
        profes = new Profesor("Francisco", "Pérez", "mañana");
        docs.add(profes.toDoc());
        
        profes = new Profesor("Noelia", "Giménez", "mañana/tarde");
        docs.add(profes.toDoc());
        
        profes = new Profesor("Miguel", "Soler", "mañana");
        docs.add(profes.toDoc());
        
        profes = new Profesor("Carmen", "Ordóñez", "tarde");
        docs.add(profes.toDoc());
        
        collection.insertMany(docs);
    }
}
