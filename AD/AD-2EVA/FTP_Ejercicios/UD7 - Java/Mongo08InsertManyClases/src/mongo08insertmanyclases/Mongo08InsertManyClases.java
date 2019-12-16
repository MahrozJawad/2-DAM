package mongo08insertmanyclases;

import clases.Asignatura;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo08InsertManyClases {

    public static void main(String[] args) {
        // Desactivar logs de MongoDB.
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        System.out.println("Conectando a MongoDB.");

        // Conexión a MongoDB, Database y colección
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("instituto");
        MongoCollection<Document> collection = db.getCollection("asignaturas");

        /*
            * Elimina el contenido de la colección, en este caso,
            * para no tener documentos duplicados.
         */
        collection.drop();

        // Insertar los documentos
        System.out.println("Insertando documentos...");

        // Creamos una lista de documentos
        ArrayList<Document> docs = new ArrayList<>();

        Asignatura asig;
        asig = new Asignatura("Acceso a datos", "Pedro Álvarez", 120);
        docs.add(asig.toDoc());

        asig = new Asignatura("Desarrollo de interfaces", "María Rodríguez", 120);
        docs.add(asig.toDoc());

        asig = new Asignatura("Programación de servicios y procesos", "Laura González", 60);
        docs.add(asig.toDoc());

        asig = new Asignatura("Programación multimedia y dispositivos móviles", "Francisco Pérez", 100);
        docs.add(asig.toDoc());
        
        collection.insertMany(docs);
        
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
