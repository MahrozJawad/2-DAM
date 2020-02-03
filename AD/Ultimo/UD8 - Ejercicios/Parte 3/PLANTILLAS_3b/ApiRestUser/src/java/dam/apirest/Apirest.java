/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.apirest;

import Clases.Frutas;
import Clases.Usuarios;
import java.util.ArrayList;
import java.util.List;
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
 * @author Mahroz
 */
@Path("/apirest")
public class Apirest {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Apirest
     */
    public Apirest() {
    }

    /**
     * Retrieves representation of an instance of dam.apirest.Apirest
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Frutas> get() {
        System.out.println("GET");
        return DAOFrutas.getAll();
    }

    /**
     * PUT method for updating or creating an instance of Apirest
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
