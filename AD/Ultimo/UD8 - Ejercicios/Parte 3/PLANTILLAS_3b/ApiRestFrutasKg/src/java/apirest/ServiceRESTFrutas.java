/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apirest;

import clases.Frutas;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/frutas")
public class ServiceRESTFrutas {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Frutas> get() {
        System.out.println("GET");
        return DAOFrutas.getAll();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("{name}")
    public Frutas get(@PathParam("name") String nombreFruta) {
        System.out.println("GET "+nombreFruta);
        return DAOFrutas.get(new Frutas(nombreFruta));
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void post(Frutas fruit) {
        System.out.println("POST");
        if(DAOFrutas.getAll().contains(fruit))
            DAOFrutas.add(fruit);
    }
    
    @PUT
    @Path("{name}/{kg}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void put(@PathParam("name") String nombreFruta, @PathParam("kg") int kg) {
        System.out.println("PUT");
        DAOFrutas.edit(nombreFruta, kg);
    }
    
    @DELETE
    @Path("{name}")
    public void delete(@PathParam("name") String nombreFruta) {
        System.out.println("DELETE "+ nombreFruta);
        DAOFrutas.delete(new Frutas(nombreFruta));
    }
}
