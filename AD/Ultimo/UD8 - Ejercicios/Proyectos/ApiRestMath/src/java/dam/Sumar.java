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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author alumno
 */
@Path("aritmetica")
public class Sumar {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Sumar
     */
    public Sumar() {
    }

    /**
     * Retrieves representation of an instance of dam.Sumar
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/sumar/{operando1}/{operando2}")
    public int getSumar(@PathParam("operando1") int num1,@PathParam("operando2") int num2) {
        return num1 + num2;
    }
}
