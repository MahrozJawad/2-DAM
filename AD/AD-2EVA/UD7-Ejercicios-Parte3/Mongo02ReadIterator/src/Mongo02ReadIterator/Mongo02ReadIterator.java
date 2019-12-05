package Mongo02ReadIterator;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo02ReadIterator {

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

        // Creamos un filtro
        Document filtros = Document.parse(
                "{$or:[ {comunidad:{$regex: '^Cas'}}, {poblacion:{$gt: 5000000}} ]}");

        // Mostramos los datos.
        System.out.println("Mostrar los datos obtenidos (Parseo)");
        System.out.println("====================================");
        MongoCursor<Document> cursor = collection.find(filtros).iterator();
        while (cursor.hasNext()) {
// DBOject de MongoDB parseado a json
            Document dbObj = Document.parse(cursor.next().toJson());
// Recuperamos campo comunidad
            if (dbObj.containsKey("comunidad")) {
                System.out.println("Comunidad: " + dbObj.get("comunidad").toString());
            }
// Recuperamos campo poblacion
            if (dbObj.containsKey("poblacion")) {
                System.out.println("Población: " + dbObj.get("poblacion").toString());
            }
// Recuperamos campo superficie
            if (dbObj.containsKey("superficie")) {
                System.out.println("Superficie: " + dbObj.get("superficie").toString());
            }
// Recuperamos el Array de provincias.
            if (dbObj.containsKey("provincias")) {
                ArrayList<String> prov = (ArrayList<String>) dbObj.get("provincias");
                System.out.print("Provincias: ");
                for (Object pr : prov) {
                    System.out.print(pr + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
