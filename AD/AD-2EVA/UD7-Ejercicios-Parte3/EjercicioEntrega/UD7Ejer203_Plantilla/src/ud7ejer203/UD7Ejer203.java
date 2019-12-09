package ud7ejer203;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import packUtils.Menu;



public class UD7Ejer203 {
    // Conexión a MongoDB, Database y Colección
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;    

    public static void Conectar() {
       // Desactivar logs de MongoDB.
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Conexión a MongoDB, Database y Colección
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("instituto");
        collection = database.getCollection("asignaturas");        
    }
    
    public static void Desconectar() {
        // Desconexión		
        System.out.println("Desconectando de MongoDB.");
        mongoClient.close();          
    }
    
 
    
    
    // ***************************************************
    // MAIN
    // ***************************************************
    public static void main(String[] args) {
        Conectar();
        Menu.Mostrar();
        Desconectar();
    }

}
