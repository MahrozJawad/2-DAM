package apirest01get;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ApiRest01Get {

    public static void main(String[] args) {
        // Objetos para realizar la petición
        Client client = ClientBuilder.newClient();
        WebTarget webTargetBase;
        WebTarget webTargetSolicitud;
        Invocation.Builder invbuilder;
        Response response;
// Indicar la URL del API Rest
        webTargetBase = client.target("http://www.riconet.es/fp/apirest");
        webTargetSolicitud = webTargetBase.path("/libros/4");
// Aportar los formatos y datos a la llamada
        invbuilder = webTargetSolicitud.request();
        invbuilder.header("Content-Type", "application/json");
        invbuilder.accept(MediaType.APPLICATION_JSON);
// Ejecutar el método
        response = invbuilder.get();

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
