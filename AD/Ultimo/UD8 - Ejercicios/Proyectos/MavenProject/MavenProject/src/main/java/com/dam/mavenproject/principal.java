package com.dam.mavenproject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class principal {

    public static void main(String[] args) {
        // Desactivar logs de MongoDB
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        System.out.println("Conectando a MongoDB.");
// Conexión a MongoDB.
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        System.out.println("Seleccionando BD y colección.");
        MongoDatabase database = mongoClient.getDatabase("demografia");
        MongoCollection<Document> collection = database.getCollection("comunidades");
// Información
        System.out.println("En la BD .........\'" + database.getName() + "\'");
        System.out.println("En la Colección ..\'" + collection.getNamespace() + "\' ");
        System.out.println("El número de documentos es " + collection.countDocuments());
// Desconexión
        System.out.println("Desconectando de MongoDB.");
        mongoClient.close();

    }
}
