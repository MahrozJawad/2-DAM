
package ud7ejer203;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
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

        System.out.println("   Mostar ASIGNATURAS ORDENADO POR NOMBRE   ");
        System.out.println(" ****************************************** ");
        Bson orden = Sorts.ascending("asignatura");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| Nombre                                             |");
        System.out.println("+----------------------------------------------------+");

        for (Document doc : collection.find().sort(orden)) {
            System.out.printf("| %-50s | \n", doc.get("asignatura").toString());
        }
        System.out.println("+----------------------------------------------------+");
    }
     
    public static void MostrarCargahoraria() {
        MongoDatabase database = mongoClient.getDatabase("instituto");
        MongoCollection<Document> collection = database.getCollection("asignaturas");

        System.out.println("   Mostar ASIGNATURAS ORDENADO POR NOMBRE   ");
        System.out.println(" ****************************************** ");
        Bson orden = Sorts.descending("horas");
        System.out.println("+----------------------------------------------------|----------+");
        System.out.println("| Nombre                                             | Horas    |");
        System.out.println("+----------------------------------------------------|----------+");

        for (Document doc : collection.find().sort(orden)) {
            System.out.printf("| %-50s | ", doc.get("asignatura").toString());
            System.out.printf("%-8s | \n", doc.get("horas").toString());
        }
        System.out.println("+---------------------------------------------------------------+");
        
    }
    
    public static void MostrarAsignaturasDuracuionMedia() {
        MongoDatabase database = mongoClient.getDatabase("instituto");
        MongoCollection<Document> collection = database.getCollection("asignaturas");

        System.out.println("   Mostar ASIGNATURAS ORDENADO POR NOMBRE   ");
        System.out.println(" ****************************************** ");
        Bson orden = Sorts.ascending("horas");
        System.out.println("+----------------------------------------------------|----------+");
        System.out.println("| Nombre                                             | Horas    |");
        System.out.println("+----------------------------------------------------|----------+");

        Document filtro = Document.parse("{$and: [{horas :{$gte: 60}}, {horas :{$lte: 100}}]}");
        for (Document doc : collection.find(filtro).sort(orden)) {
            System.out.printf("| %-50s | ", doc.get("asignatura").toString());
            System.out.printf("%-8s | \n", doc.get("horas").toString());
        }
        System.out.println("+---------------------------------------------------------------+");
    }
    
    public static void main(String[] args) {
        Conectar();
        Menu.Mostrar();
        Desconectar();
    }
}
