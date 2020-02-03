
package apirest;

import clases.Usuarios;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class BDUsuarios {
    
    // Desactivar logs de MongoDB.
    private final static Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
    static { // Este bloque estático se ejecuta al cargar la clase. Funciona como un constructor de la clase
        mongoLogger.setLevel(Level.SEVERE);
    }

    // Conexión a MongoDB, Database y Colección    
    private final static MongoClient mongoClient = new MongoClient("localhost", 27017);;
    private final static MongoDatabase db = mongoClient.getDatabase("apirest");
    private final static MongoCollection<Document> collection = db.getCollection("usuarios");  

    // GSON
    private final static Gson gson = new Gson();
    
    // GETALL
    public static ArrayList<Usuarios> usersGetAll() {  
        ArrayList<Usuarios> listaUser = new ArrayList<>();
        
        MongoCursor<Document> cursor = collection.find().sort(Sorts.ascending("id")).iterator();

        while (cursor.hasNext()) {
          Usuarios cli = gson.fromJson(cursor.next().toJson(), Usuarios.class);        
          listaUser.add(cli);
        }
        return listaUser;
    }

    // GET
    public static Usuarios userget(String id) {  
        
        Usuarios user = null;
        Document d = collection.find(Filters.eq("id",id)).first();     
        if (d==null) {
            System.out.println("Cliente no existe");
        } else {
            user = new Usuarios();
            user.setId(d.getString("id"));
            user.setNombre(d.getString("nombre"));
            user.setEdad(d.getInteger("edad"));
            user.setIdioma(d.getString("idioma"));
        }
        return user;
    }
    
    // PUT - Idioma y edad
    public static boolean userPutIdiomaYEdad(String id, Usuarios user) {
        
        try {
            collection.updateOne(Filters.eq("id",id), Updates.set("idioma",user.getIdioma()));         
            collection.updateOne(Filters.eq("id",id), Updates.set("edad",user.getEdad())); 
        } catch (Exception e) {
            return false;
        }
        return true;
        
    }   
    // Existe
    public static boolean usuarioExiste(Usuarios user) {
        try {
            String json = gson.toJson(user);
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
    
}
