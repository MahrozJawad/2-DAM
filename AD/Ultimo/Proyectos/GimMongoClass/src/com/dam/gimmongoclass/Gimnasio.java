package com.dam.gimmongoclass;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bson.Document;

public class Gimnasio {

    public static Cliente obtieneCliente(String dni) {

        // Desactivar logs de MongoDB 
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        System.out.println("Conectando a MongoDB.");

        // Conexión a MongoDB.
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        System.out.println("Seleccionando BD y colección.");
        MongoDatabase database = mongoClient.getDatabase("gimnasio");
        MongoCollection<Document> collection = database.getCollection("clientes");

        // Información
        System.out.println("En la BD .........\'" + database.getName() + "\'");
        System.out.println("En la Colección ..\'" + collection.getNamespace() + "\' ");
        System.out.println("El número de documentos es " + collection.countDocuments());

        Document filtro = Document.parse("{dni:'" + dni + "'}");
        Document cliDoc = collection.find(filtro).first();

        Cliente cliente = null;
        if (cliDoc != null) {
            cliente = new Cliente();
            cliente.setDni((String) cliDoc.get("dni"));
            cliente.setNombre((String) cliDoc.get("nombre"));
            cliente.setApellidos((String) cliDoc.get("apellidos"));
            cliente.setFecha_nac((String) cliDoc.get("fecha_nac"));
            cliente.setTelefono((String) cliDoc.get("telefono"));
        }

        // Desconexión		
        System.out.println("Desconectando de MongoDB.");
        mongoClient.close();
        
        return cliente;

    }

}
