
package apirest06;

import clases.Libros;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class ApiRest06 {

    public static void main(String[] args) {
        // Objetos para realizar la petición
        Client client = ClientBuilder.newClient();

        Response response = client.target("http://www.riconet.es/fp/apirest")
                      .path("/libros")
                      .request()
                      .header("Content-Type", "application/json")
                      .accept(MediaType.APPLICATION_JSON)
                      .get();    

        // Mostrar la respuesta obtenida
        System.out.println("Status:");
        System.out.println("=======");
        System.out.println(response.getStatus());
        System.out.println();
        
        System.out.println("Data Body:");
        System.out.println("==========");

        ArrayList<Libros> listaLib;
        listaLib = response.readEntity(new GenericType<ArrayList<Libros>>() {});
        System.out.println(listaLib);

        System.out.println();
    }
}
