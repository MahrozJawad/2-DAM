package mongo10deleteone;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo10DeleteOne {

    public static void main(String[] args) {
        // Desactivar logs de MongoDB.
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        System.out.println("Conectando a MongoDB.");

        // Conexión a MongoDB, Database y colección
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("instituto");
        MongoCollection<Document> collection = db.getCollection("asignaturas");

        // Insertar los documentos
        System.out.println("Insertando documento...");

        Document doc = new Document();
        doc.append("nombre", "Lenguaje de Marcas");
        doc.append("profesor", "Noelia Smith");
        doc.append("horas", 100);
        collection.insertOne(doc);

        // Mostramos los datos con for
        System.out.println("Mostrar los datos obtenidos (BSON).");
        for (Document cursor : collection.find()) {
            System.out.println(cursor.toJson());
        }
        
        // Eliminando un documento.
        collection.deleteOne(Filters.eq("profesor","Noelia Smith"));
        
        // Mostramos los datos con for
        System.out.println("Mostrar los datos obtenidos (BSON).");
        for (Document cursor : collection.find()) {
            System.out.println(cursor.toJson());
        }
        

        // Desconexión		
        System.out.println("Desconectando de MongoDB.");
        mongoClient.close();

    }
}
