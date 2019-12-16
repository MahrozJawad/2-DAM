package mongo13gimnasio;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo13Gimnasio {

    public static void main(String[] args) {
        // Desactivar logs de MongoDB.
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Conexión a MongoDB, Database y Colección
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("gimnasio");
        MongoCollection<Document> colClases = database.getCollection("clases");
        MongoCollection<Document> colMonitores = database.getCollection("monitores");
        MongoCollection<Document> colClientes = database.getCollection("clientes");

        
        // Mostramos los datos.
        System.out.println("Clases del GIMNASIO");
        System.out.println("====================================");
        MongoCursor<Document> cursor = colClases.find().sort(Sorts.descending("nombre")).iterator();

        while (cursor.hasNext()) {
            // DBOject de MongoDB parseado a json
            Document oClase = Document.parse(cursor.next().toJson());
            
            System.out.println("Código.........: " + oClase.get("cod_clase").toString());
            System.out.println("Nombre.........: " + oClase.get("nombre").toString());
            System.out.println("Horas semanales: " + oClase.get("horas_sem").toString());
            
            // Monitor
            Document oMonitor = colMonitores.find(Filters.eq("id_monitor", oClase.get("impartida_por").toString() )).iterator().next();
            System.out.println("Monitor........: " + oMonitor.get("nombre") + " " + oMonitor.get("apellidos") );       

            // Clientes 1 -> recorriendo la lista de Clientes
            ArrayList<String> asisten = (ArrayList<String>) oClase.get("asisten");
            System.out.println("Asisten: ");
            for (Object cli : asisten) {
                Document oCliente = colClientes.find(Filters.eq("dni", cli )).iterator().next();
                System.out.println(" -> " +  oCliente.get("nombre") + " " + oCliente.get("apellidos") + "(" + oCliente.get("telefono") + ")" );
            }
            
            // Clientes 2 -> Utilizando Filters.in
            System.out.println("Asisten: ");
            for (Document cli : colClientes.find(Filters.in("dni", (ArrayList<String>) oClase.get("asisten")) )) {
                System.out.println(" -> " +  cli.get("nombre") + " " + cli.get("apellidos") + "(" + cli.get("telefono") + ")" );
            }
            
            
            
            System.out.println("====================================");
            System.out.println();

        }

        // Desconexión		
        System.out.println("Desconectando de MongoDB.");
        mongoClient.close();

    }
}
