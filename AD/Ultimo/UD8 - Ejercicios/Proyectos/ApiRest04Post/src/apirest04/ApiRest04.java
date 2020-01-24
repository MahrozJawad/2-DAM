
package apirest04;

import java.io.StringReader;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class ApiRest04 {

    public static void main(String[] args) {
        
        // DECLARACIÓN DE VARIABLES
        String body;
        JsonReader jsonReader;
        JsonObject json;
        
        int id;
        String titulo;
        String autor;
                       
        Scanner teclado = new Scanner(System.in);
        
        Response responseCount;
        Response responsePost;
        
        // Objetos para realizar la petición
        Client client = ClientBuilder.newClient();

        responseCount = client.target("http://www.riconet.es/fp/apirest")
                      .path("/libros/count")
                      .request()
                      .header("Content-Type", "application/json")
                      .accept(MediaType.APPLICATION_JSON)
                      .get();    
	if (responseCount.getStatus() == 200){
            body = responseCount.readEntity(String.class);
            // System.out.println("Response: " + body);
		
            jsonReader = Json.createReader(new StringReader(body));
            json = jsonReader.readObject();
            
            // ***************
            // DATOS DEL LIBRO
            id = Integer.valueOf(json.getString("total_registros"));
            id = id +1;
            System.out.println("Id Libro: "+id);
            
            System.out.print("Introduzca título: ");
            titulo = teclado.nextLine();
            
            System.out.print("Introduzca autor: ");
            autor = teclado.nextLine();
            
            // ***************
            // JSON DEL LIBRO
            String libro = "{ 'id': "+id+", 'titulo':'"+titulo+"', 'autor':'"+autor+"' }";
            libro = libro.replaceAll("'", "\"");
            System.out.println(libro);
            
            // ***************
            // POST DEL LIBRO
            responsePost = client.target("http://www.riconet.es/fp/apirest")
                          .path("/libros/")
                          .request()
                          .header("Content-Type", "application/json")
                          .accept(MediaType.APPLICATION_JSON)
                          .post(Entity.json(libro));    
            if (responsePost.getStatus() == 201){
                body = responsePost.readEntity(String.class);
                // System.out.println("Response: " + body);

                jsonReader = Json.createReader(new StringReader(body));
                json = jsonReader.readObject();
                System.out.println(json.getString("mensaje"));
            } else {
                body = responsePost.readEntity(String.class);
                // System.out.println("Response: " + body);
                
                jsonReader = Json.createReader(new StringReader(body));
                json = jsonReader.readObject();
                if (json.containsKey("mensaje")) {
                    System.out.println("ERROR: "+json.getString("mensaje"));
                }
                if (json.containsKey("sqlError")) {
                    System.out.println(json.getString("sqlError"));
                }
            }
            
	} else {
            body = responseCount.readEntity(String.class);
            // System.out.println("Response: " + body);
		
            jsonReader = Json.createReader(new StringReader(body));
            json = jsonReader.readObject();
            if (json.containsKey("mensaje")) {
                System.out.println("ERROR: "+json.getString("mensaje"));
            }
        }
        

    }
}
