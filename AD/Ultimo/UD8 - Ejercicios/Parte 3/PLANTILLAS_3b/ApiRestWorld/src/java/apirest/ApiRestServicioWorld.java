/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apirest;

import clases.City;
import clases.Country;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Mahroz
 */
@Path("")
public class ApiRestServicioWorld {

    @Context
    private UriInfo context;

    public ApiRestServicioWorld() {
        
    }

    @GET
    @Path("city/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public City getCiudad(@PathParam("id") int id) {
        return BDWorld.getCity(id);
    }
    
    @GET
    @Path("country/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Country getCountry(@PathParam("code") String code) {
        return BDWorld.getCountry(code);
    }
}
