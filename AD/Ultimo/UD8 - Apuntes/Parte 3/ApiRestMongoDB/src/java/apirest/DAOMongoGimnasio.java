
package apirest;

import clases.Clientes;
import clases.Monitores;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;


// ******************************************
// DAO = Data Access Object
// Librería de acceso a datos de MongoDB
// Base de Datos: gimnasio
// Tablas: clientes, monitores
// ******************************************

public class DAOMongoGimnasio {
    // Desactivar logs de MongoDB.
    private final static Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
    static { // Este bloque estático se ejecuta al cargar la clase. Funciona como un constructor de la clase
        mongoLogger.setLevel(Level.SEVERE);
    }

    // Conexión a MongoDB, Database y Colección    
    private final static MongoClient mongoClient = new MongoClient("localhost", 27017);;
    private final static MongoDatabase db = mongoClient.getDatabase("gimnasio");;
    private final static MongoCollection<Document> collection = db.getCollection("clientes");  

    // GSON
    private final static Gson gson = new Gson();

    // **************************
    // Existe
    public static boolean clientesExiste(Clientes cli) {
        try {
            String json = gson.toJson(cli);
            Document d = Document.parse(json);
            if (collection.countDocuments(d)>0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }        

    // **************************
    // POST
    public static boolean clientesPost(Clientes cli) {
        try {
            String json = gson.toJson(cli);
            Document d = Document.parse(json);
            if (clientesExiste(cli)) {
                return false;
            } else {
                collection.insertOne(d);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    // **************************
    // GETALL
    public static ArrayList<Clientes> clientesGetAll() {  
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        
        MongoCursor<Document> cursor = collection.find().sort(Sorts.ascending("dni")).iterator();

        while (cursor.hasNext()) {
          Clientes cli = gson.fromJson(cursor.next().toJson(), Clientes.class);        
          listaClientes.add(cli);
        }
        return listaClientes;
    }


    // **************************
    // GET
    public static Clientes clientesGet(String dni) {  
        
        Clientes cliente = null;
        Document d = collection.find(Filters.eq("dni",dni)).first();     
        if (d==null) {
            System.out.println("Cliente no existe");
        } else {
            cliente = new Clientes();
            cliente.setDni(d.getString("dni"));
            cliente.setNombre(d.getString("nombre"));
            cliente.setApellidos(d.getString("apellidos"));
            cliente.setFecha_nac(d.getString("fecha_nac"));
            cliente.setTelefono(d.getString("telefono"));
        }
        return cliente;
    }
       
    // **************************
    // PUT - TELEFONO
    public static void clientesPutTelefono(String dni, Clientes cli) {
        collection.updateOne(Filters.eq("dni",dni), Updates.set("telefono",cli.getTelefono()));        
    }    




    // **************************
    // GETALL
    public static ArrayList<Monitores> monitoresGetAll() {  
        ArrayList<Monitores> listaMonitores = new ArrayList<>();
        
        MongoCursor<Document> cursor = collection.find().sort(Sorts.ascending("id_monitor")).iterator();

        while (cursor.hasNext()) {
          Monitores mon = gson.fromJson(cursor.next().toJson(), Monitores.class);        
          listaMonitores.add(mon);
        }
        return listaMonitores;
    }


    // **************************
    // GET
    public static Monitores monitoresGet(String idMonitor) {  
        
        Monitores monitor = null;
        Document d = collection.find(Filters.eq("id_monitor",idMonitor)).first();     
        if (d==null) {
            System.out.println("Monitor no existe");
        } else {
            monitor = new Monitores();
            monitor.setId_monitor(d.getString("id_monitor"));
            monitor.setNombre(d.getString("nombre"));
            monitor.setApellidos(d.getString("apellidos"));
        }
        return monitor;
    }


    
}