package redsocial01consultas;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import packUtils.*;
import packUtils.Menu;

public class RedSocial01Consultas {

    // Conexión a MongoDB, Database y Colección
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    public static void MujeresActivas() {
        System.out.println(UtilString.StrRepetir('-', 40));
        System.out.println("Mostrar Mujeres en estado activo");
        System.out.println(UtilString.StrRepetir('=', 30));

        Document filter = Document.parse(
                "{\n"
                + "        \"sexo\":\"M\",\n"
                + "        \"activo\":true\n"
                + "    }"
        );

        System.out.println("+--------+--------------------+----------------------+------+----------+");
        System.out.println("| Codigo | Nombre             | Apellidos            | Sexo | Activo   |");
        System.out.println("+--------+--------------------+----------------------+------+----------+");

        for (Document c : collection.find(filter)) {
            System.out.printf("| %6d | %-18s | %-20s | %-4s | %-8s |\n",
                    c.getInteger("_id"), c.getString("nombre"), c.getString("apellidos"), c.getString("sexo"), c.getBoolean("activo"));
        }

        System.out.println("+--------+--------------------+----------------------+------+----------+");
    }

    public static void HombresConMasMensajes() {
        System.out.println(UtilString.StrRepetir('-', 40));
        System.out.println("Mostrar Mujeres en estado activo");
        System.out.println(UtilString.StrRepetir('=', 30));

        Document filter = Document.parse(
                "{\n"
                + "        \"sexo\":\"V\",\n"
                + "        \"total_mensajes\":{$gt:1}\n"
                + "    }"
        );

        System.out.println("+--------+--------------------+----------------------+------+----------+");
        System.out.println("| Codigo | Nombre             | Apellidos            | Sexo | Activo   |");
        System.out.println("+--------+--------------------+----------------------+------+----------+");

        for (Document c : collection.find(filter)) {
            System.out.printf("| %6d | %-18s | %-20s | %-4s | %-8s |\n",
                    c.getInteger("_id"), c.getString("nombre"), c.getString("apellidos"), c.getString("sexo"), c.getBoolean("activo"));
        }

        System.out.println("+--------+--------------------+----------------------+------+----------+");
    }

    public static void UsuariosFutbolOCine() {
        System.out.println(UtilString.StrRepetir('-', 40));
        System.out.println("Mostrar Mujeres en estado activo");
        System.out.println(UtilString.StrRepetir('=', 30));

        Document filter = Document.parse(
                "{\n"
                + "        $or:[\n"
                + "            {\"grupos\":\"cine\"},\n"
                + "            {\"grupos\":\"fútbol\"}\n"
                + "        ]\n"
                + "    }"
        );

        System.out.println("+--------+--------------------+----------------------+");
        System.out.println("| Codigo | Nombre             | Apellidos            |");
        System.out.println("+--------+--------------------+----------------------+");

        for (Document c : collection.find(filter)) {
            System.out.printf("| %6d | %-18s | %-20s |\n",
                    c.getInteger("_id"), c.getString("nombre"), c.getString("apellidos"));
        }

        System.out.println("+--------+--------------------+----------------------+");
    }
    
    public static void UsuariosFutbolYCine() {
        System.out.println(UtilString.StrRepetir('-', 40));
        System.out.println("Mostrar Mujeres en estado activo");
        System.out.println(UtilString.StrRepetir('=', 30));

        Document filter = Document.parse(
                "{\n"
                + "        $and:[\n"
                + "            {\"grupos\":\"cine\"},\n"
                + "            {\"grupos\":\"fútbol\"}\n"
                + "        ]\n"
                + "    }"
        );

        System.out.println("+--------+--------------------+----------------------+");
        System.out.println("| Codigo | Nombre             | Apellidos            |");
        System.out.println("+--------+--------------------+----------------------+");

        for (Document c : collection.find(filter)) {
            System.out.printf("| %6d | %-18s | %-20s |\n",
                    c.getInteger("_id"), c.getString("nombre"), c.getString("apellidos"));
        }

        System.out.println("+--------+--------------------+----------------------+");
    }
    
    public static void Conectar() {
        // Desactivar logs de MongoDB.
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Conexión a MongoDB, Database y Colección
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("redsocial");
        collection = database.getCollection("usuarios");
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
