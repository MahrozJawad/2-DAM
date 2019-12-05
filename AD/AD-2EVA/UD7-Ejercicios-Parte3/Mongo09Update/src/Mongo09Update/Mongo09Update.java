package Mongo09Update;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo09Update {

    public static void main(String[] args) {

        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
// Crear cliente
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Seleccionamos la BD, si no existe la crea
        MongoDatabase db = mongoClient.getDatabase("instituto");
// Seleccionamos la colección, si no existe la crea
        MongoCollection<Document> collection = db.getCollection("asignaturas");

// Actualizando los documentos
        System.out.println("Actualizando documentos...");
// Actualizamos un sólo documento.
        collection.updateOne(
                Filters.eq("nombre", "Desarrollo de interfaces"),
                Updates.set("profesor", "Manuel Sánchez")
        );

        System.out.println("Finalizado");
        mongoClient.close();
    }
}
