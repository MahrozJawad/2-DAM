
package ud7ejer203;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import packUtils.Menu;

public class UD7Ejer203 {

    static MongoClient mongoClient;
    
    private static void Conectar() {
        // Log de MongoDB
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
// Crear cliente
        mongoClient = new MongoClient("localhost", 27017);
    }
    private static void Desconectar() {
        mongoClient.close();
    }
    public static void MostrarAsignaturasPorNombre() {
        
        MongoDatabase database = mongoClient.getDatabase("instituto");

        MongoCollection<Document> collection = database.getCollection("asignaturas");
        for(Document cursor : collection.find()) {
            System.out.println(cursor.toJson());
        }
        
    }
    public static void MostrarCargahoraria() {
        
    }
    
    public static void MostrarAsignaturasDuracuionmedia() {
        
    }
    
    public static void main(String[] args) {
        Conectar();
        Menu.Mostrar();
        Desconectar();
    }
}
