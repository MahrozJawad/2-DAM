package ud7ejer202;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class UD7Ejer202 {

    public static void main(String[] args) {
        // Log de MongoDB
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
// Crear cliente
        MongoClient mongoClient = new MongoClient("localhost", 27017);
// Seleccionar base de datos
        MongoDatabase database = mongoClient.getDatabase("instituto");

        MongoCollection<Document> collection = database.getCollection("profesores");
// Actualizando los documentos
        System.out.println("Actualizando documentos...");
/*
* Añadimos a todos los documentos, que tengan el campo nombre,
* el nuevo campo curso.
         */
        collection.updateMany(
                Filters.or(
                    Filters.and(
                        Filters.eq("nombre", "Manuel"),
                        Filters.eq("apellido", "Sánchez")
                    ),
                    Filters.and(
                        Filters.eq("nombre", "Laura"),
                        Filters.eq("apellido", "González")
                    )
                ),
                Updates.set("turno", "mañana/tarde")
        );
    }
}
