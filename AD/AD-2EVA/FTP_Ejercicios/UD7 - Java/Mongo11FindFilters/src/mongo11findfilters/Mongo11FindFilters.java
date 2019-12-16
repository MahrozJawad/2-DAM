package mongo11findfilters;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo11FindFilters {

    public static void main(String[] args) {
        // Desactivar logs de MongoDB.
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Conexión a MongoDB, Database y Colección
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("demografia");
        MongoCollection<Document> collection = database.getCollection("comunidades");

        // Filtro utilizando Document.parse
        System.out.println("Filtro utilizando Document.parse");
        System.out.println("================================");
        for (Document cur : collection.find(
                Document.parse("{ \"$and\" : [ " +
                        "{ \"poblacion\" : { \"$gte\" : 2000000 } }, "+
                        "{ \"poblacion\" : { \"$lt\" : 5000000 } } "+
                        "] }") )) {
                               System.out.println(cur.toJson());
        }
        System.out.println();
        
        
        // Filtro utilizando Document sin parse
        System.out.println("Filtro utilizando Document sin parse");
        System.out.println("====================================");
        for (Document cur : collection.find(
                new Document("poblacion",
                        new Document("$gte", 2000000)
                                .append("$lt", 5000000)))) {
            System.out.println(cur.toJson());
        }
        System.out.println();

        // Filtro utilizando Filters 
        System.out.println("Filtro utilizando Filters");
        System.out.println("=========================");
        for (Document cur : collection.find(
                and(
                        gte("poblacion", 2000000),
                        lt("poblacion", 5000000)))) {
            System.out.println(cur.toJson());
        }
        System.out.println();
        
        // Filtro utilizando Filters indicando la clase
        System.out.println("Filtro utilizando Filters indicando la clase");
        System.out.println("============================================");
        for (Document cur : collection.find(
                Filters.and(
                        Filters.gte("poblacion", 2000000),
                        Filters.lt("poblacion", 5000000)))) {
            System.out.println(cur.toJson());
        }
        System.out.println();        
        
        // Desconexión		
        System.out.println("Desconectando de MongoDB.");
        mongoClient.close();

    }
}
