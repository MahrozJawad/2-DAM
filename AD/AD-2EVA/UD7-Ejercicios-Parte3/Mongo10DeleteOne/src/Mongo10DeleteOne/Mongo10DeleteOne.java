package Mongo10DeleteOne;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo10DeleteOne {

    public static void main(String[] args) {

        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
// Crear cliente
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Seleccionamos la BD, si no existe la crea
        MongoDatabase db = mongoClient.getDatabase("instituto");
// Seleccionamos la colección, si no existe la crea
        MongoCollection<Document> collection = db.getCollection("asignaturas");

// Insertar los documentos
        //System.out.println("Insertando documento...");
        //Document doc = new Document();
        //doc.append("nombre", "Lenguaje de Marcas");
        //doc.append("profesor", "Noelia Smith");
        //doc.append("horas", 100);
        //collection.insertOne(doc);
// Mostramos los datos con for
        //System.out.println("Mostrar los datos obtenidos (BSON).");
        //for (Document cursor : collection.find()) {
        //  System.out.println(cursor.toJson());
        //}
// Eliminando un documento.
        System.out.println("Eliminar el dato obtenidos (BSON).");
        collection.deleteOne(Filters.eq("profesor", "Noelia Smith"));

        System.out.println("Finalizado");
        mongoClient.close();
    }
}
