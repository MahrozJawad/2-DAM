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
        Asignatura asig;
        asig = new Asignatura("Acceso a datos", "Pedro Álvarez", 120);
        docs.add(asig.toDoc());
        asig = new Asignatura("Desarrollo de interfaces", "María Rodríguez", 120);
        docs.add(asig.toDoc());
        asig = new Asignatura("Programación de servicios y procesos",
                "Laura González", 60);
        docs.add(asig.toDoc());
        asig = new Asignatura("Programación multimedia y dispositivos móviles",
                "Francisco Pérez", 200);
        docs.add(asig.toDoc());
        collection.insertMany(docs);

        System.out.println("Finalizado");
        mongoClient.close();
    }
}
