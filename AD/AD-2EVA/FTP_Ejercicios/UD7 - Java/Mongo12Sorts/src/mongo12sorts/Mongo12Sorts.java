package mongo12sorts;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import com.mongodb.client.model.Sorts;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Mongo12Sorts {

    public static void main(String[] args) {
        // Desactivar logs de MongoDB.
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Conexión a MongoDB, Database y Colección
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("instituto");
        MongoCollection<Document> collection = database.getCollection("asignaturas");

 	// Utilizando Filters y Sorts
	Bson filtros = Filters.gt("horas", 90);
	Bson orden = Sorts.ascending("profesor");
	for (Document cursorLibro : collection.find(filtros).sort(orden)) {
		System.out.println(cursorLibro.toJson());
	}      
        
        // Desconexión		
        System.out.println("Desconectando de MongoDB.");
        mongoClient.close();

    }
}
