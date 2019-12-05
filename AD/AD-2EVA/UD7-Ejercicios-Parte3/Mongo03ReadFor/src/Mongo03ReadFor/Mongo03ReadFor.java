package Mongo03ReadFor;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo03ReadFor {

    public static void main(String[] args) {
        // Log de MongoDB
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
// Crear cliente
        MongoClient mongoClient = new MongoClient("localhost", 27017);
// Seleccionar base de datos
        MongoDatabase database = mongoClient.getDatabase("demografia");
// Seleccionar la colección
        MongoCollection<Document> collection = database.getCollection("comunidades");
// Mostrar información
        System.out.println("BD .........\'" + database.getName() + "\'");
        System.out.println("Colección ..\'" + collection.getNamespace() + "\' ");
        System.out.println("Número de documentos es " + collection.countDocuments());
        
        // Mostramos los datos con while
        System.out.println("\nMostrar los datos obtenidos (BSON).");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }

        mongoClient.close();
    }
}
