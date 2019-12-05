package Mongo07InsertMany;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo07InsertMany {

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
// Creamos una lista de documentos
        ArrayList<Document> docs = new ArrayList<>();
        Document doc = new Document();
        doc.append("asignatura", "Acceso a datos");
        doc.append("profesor", "Pedro Álvarez");
        doc.append("horas", 120);
        docs.add(doc);
        doc = new Document();
        doc.append("asignatura", "Desarrollo de interfaces");
        doc.append("profesor", "María Rodríguez");
        doc.append("horas", 120);
        docs.add(doc);
        doc = new Document();
        doc.append("asignatura", "Programación de servicios y procesos");
        doc.append("profesor", "Laura González");
        doc.append("horas", 60);
        docs.add(doc);
        doc = new Document();
        doc.append("asignatura", "Programación multimedia y dispositivos móviles");
        doc.append("profesor", "Francisco Pérez");
        doc.append("horas", 100);
        docs.add(doc);
        collection.insertMany(docs);

        System.out.println("Finalizado");
        mongoClient.close();
    }
}
