package apirest02get;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ApiRest02Get {

    public static void main(String[] args) {
        // Objetos para realizar la petición
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://www.riconet.es/fp/apirest")
                .path("/libros/4")
                .request()
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_XML)
                .get();
// Mostrar la respuesta obtenida
        System.out.println("Status:");
        System.out.println("=======");
        System.out.println(response.getStatus());
        System.out.println();
        System.out.println("Data Body:");
        System.out.println("==========");
        System.out.println(response.readEntity(String.class));
        System.out.println();
    }
}
