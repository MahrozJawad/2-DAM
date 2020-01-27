/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author alumno
 */
@Path("holamundo")
public class HolaMundo {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HolaMundo
     */
    public HolaMundo() {
    }

    /**
     * Retrieves representation of an instance of dam.HolaMundo
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Mi primer api rest</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>Hola Mundo Pruebas</h1>\n"
                + "    </body>\n"
                + "</html>";
    }
}
