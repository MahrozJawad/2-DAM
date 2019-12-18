package RedSocial02Agrupaciones;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import packUtils.*;
import packUtils.Menu;

public class RedSocial02Agrupaciones {

    // Conexión a MongoDB, Database y Colección
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    public static void AmigosDeUsuarios() {
        System.out.println(UtilString.StrRepetir('-', 40));
        System.out.println("Mostrar Amigos de usuarios");
        System.out.println(UtilString.StrRepetir('=', 30));

        for (Document c : collection.find().sort(Sorts.ascending("_id"))) {
            System.out.println(c.getInteger("_id") + " " + c.getString("nombre"));

            if (c.containsKey("amigos")) {
                ArrayList<Integer> idAmigo = (ArrayList<Integer>) c.get("amigos");
                for (int id : idAmigo) {
                    Document filter = Document.parse(
                            "{\n"
                            + "    \"_id\":{$eq:" + id + "}\n"
                            + "}"
                    );
                    Document d = collection.find(filter).first();
                    System.out.println(" => " + d.getInteger("_id") + " " + d.getString("nombre"));

                }
            }
        }
    }

    public static void UsuarioMasJoven() {
        System.out.println(UtilString.StrRepetir('-', 40));
        System.out.println("Mostrar Mujeres en estado activo");
        System.out.println(UtilString.StrRepetir('=', 30));

        System.out.println("+--------+--------------------+----------------------+------+-------------------------------+");
        System.out.println("| Codigo | Nombre             | Apellidos            | Sexo | Nacimiento                    |");
        System.out.println("+--------+--------------------+----------------------+------+-------------------------------+");

        Document c = collection.find().sort(Sorts.descending("nacimiento")).first();
        System.out.printf("| %6d | %-18s | %-20s | %-4s | %-29s |\n",
                c.getInteger("_id"), c.getString("nombre"), c.getString("apellidos"), c.getString("sexo"), c.getDate("nacimiento"));

        System.out.println("+--------+--------------------+----------------------+------+-------------------------------+");
    }

    public static void PaisesYCiudades() {
        System.out.println(UtilString.StrRepetir('-', 40));
        System.out.println("Mostrar Mujeres en estado activo");
        System.out.println(UtilString.StrRepetir('=', 30));

        ArrayList<Document> filters = new ArrayList<>();
        filters.add(Document.parse(
                "{\n"
                + "        $group:{\n"
                + "            \"_id\":{\n"
                + "                \"pais\":\"$ubicacion.pais\",\n"
                + "                \"ciudad\":\"$ubicacion.ciudad\"\n"
                + "            },\n"
                + "            \"num_Usuarios\":{$sum:1}\n"
                + "        }\n"
                + "    }"
        ));
        filters.add(Document.parse(
                "{\n"
                + "        $sort:{\n"
                + "            \"ubicacion.pais\":1,\n"
                + "            \"ubicacion.ciudad\":1\n"
                + "        }\n"
                + "    }"
        ));

        System.out.println("+--------------+------------+");
        System.out.println("| Pais         | Ciudad     |");
        System.out.println("+--------------+------------+");

        for (Document c : collection.aggregate(filters)) {
            System.out.printf("| %-12s | %-12s |\n",
                    ((Document) c.get("_id")).get("pais"), ((Document) c.get("_id")).get("ciudad"));
        }

        System.out.println("+--------------+------------+");
    }

    public static void NombresDeCadaSexo() {
        System.out.println(UtilString.StrRepetir('-', 40));
        System.out.println("Mostrar Mujeres en estado activo");
        System.out.println(UtilString.StrRepetir('=', 30));

        ArrayList<Document> filters = new ArrayList<>();
        filters.add(Document.parse(
                "{\n"
                + "        $group:{\n"
                + "            \"_id\":{\n"
                + "                \"sexo\":\"$sexo\"\n"
                + "            }\n"
                + "        }\n"
                + "    }"
        ));

        System.out.println("        +--------+--------------------+----------------------+------+----------+");
        System.out.println("        | Codigo | Nombre             | Apellidos            | Sexo | Activo   |");
        System.out.println("        +--------+--------------------+----------------------+------+----------+");

        for (Document c : collection.aggregate(filters)) {
            String sexo = ((Document) c.get("_id")).get("sexo").toString();

            System.out.println("Sexo: " + sexo);
            for (Document d : collection.find(new Document("sexo", sexo))) {
                System.out.printf("        ");
                System.out.printf("| %6d | %-18s | %-20s | %-4s | %-8s |\n",
                        d.getInteger("_id"), d.getString("nombre"), d.getString("apellidos"), d.getString("sexo"), d.getBoolean("activo"));
            }
        }
        System.out.println("        +--------+--------------------+----------------------+------+----------+");
    }

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
