package mongo06insertone;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo06InsertOne {

    public static void main(String[] args) {
        
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
// Crear cliente
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        
        // Seleccionamos la BD, si no existe la crea
        MongoDatabase db = mongoClient.getDatabase("instituto");
// Seleccionamos la colección, si no existe la crea
        MongoCollection<Document> collection = db.getCollection("asignaturas");
        /*
* Elimina el contenido de la colección, en este caso,
* para no tener documentos duplicados.
         */
        collection.drop();
// Insertar los documentos
        System.out.println("Insertando documentos...");
        Document doc = new Document();
        doc.append("asignatura", "Acceso a datos");
        doc.append("profesor", "Pedro Álvarez");
        doc.append("horas", 120);
        collection.insertOne(doc);
        doc = new Document();
        doc.append("asignatura", "Desarrollo de interfaces");
        doc.append("profesor", "María Rodríguez");
        doc.append("horas", 120);
        collection.insertOne(doc);
        doc = new Document();
        doc.append("asignatura", "Programación de servicios y procesos");
        doc.append("profesor", "Laura González");
        doc.append("horas", 60);
        collection.insertOne(doc);
        doc = new Document();
        doc.append("asignatura", "Programación multimedia y dispositivos móviles");
        doc.append("profesor", "Francisco Pérez");
        doc.append("horas", 100);
        collection.insertOne(doc);
        
        System.out.println("Finalizado");
        mongoClient.close();
    }
}
