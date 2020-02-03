/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apirest;

import clases.Usuarios;
import apirest.BDUsuarios;
import clases.ResponseEntity;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Mahroz
 */
@Path("/frutas")
public class ServiceRESTUsuarios {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Apirest
     */
    public ServiceRESTUsuarios() {
    }

    @GET
    @Path("usuarios")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Usuarios> getAll() {
        return BDUsuarios.usersGetAll();
    }
    @GET
    @Path("usuarios/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Usuarios getOne(@PathParam("id") String id) {
        return BDUsuarios.userget(id);
    }
    
    @PUT
    @Path("usuarios/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response put(@PathParam("id") String id, Usuarios entity) {
        
        ResponseEntity resp = new ResponseEntity();
        Response.Status status;
        Response response;
                
        if (!BDUsuarios.usuarioExiste(entity)) {
                status=Response.Status.BAD_REQUEST;
                resp.setStatus(status.getStatusCode());
                resp.setMensaje("Error: el usuario no existe");
        } else {
            if (BDUsuarios.userPutIdiomaYEdad(id, entity)) {
                status=Response.Status.CREATED;
                resp.setStatus(status.getStatusCode());
                resp.setMensaje("Body vacío");
            } else {
                status=Response.Status.BAD_REQUEST;
                resp.setStatus(status.getStatusCode());
                resp.setMensaje("Error al actualizar usuario");
            }
        }

        response = Response
                    .status(status)
                    .entity(resp)    
                    .build();
        
        
        return response;
    }
    
}
