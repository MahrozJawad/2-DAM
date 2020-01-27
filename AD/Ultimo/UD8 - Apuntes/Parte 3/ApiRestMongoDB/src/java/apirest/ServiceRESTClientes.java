/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apirest;

import clases.Clientes;
import clases.ResponseEntity;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("clientes")
public class ServiceRESTClientes {

    @Context
    private UriInfo context;

    public ServiceRESTClientes() {
    }

    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Clientes> getAll() {
        return DAOMongoGimnasio.clientesGetAll();
    }

    @GET
    @Path("{dni}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Clientes getOne(@PathParam("dni") String dni) {
        return DAOMongoGimnasio.clientesGet(dni);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Clientes entity) {
        
        ResponseEntity resp = new ResponseEntity();
        Response.Status status;
        Response response;
                
        if (DAOMongoGimnasio.clientesExiste(entity)) {
                status=Response.Status.BAD_REQUEST;
                resp.setStatus(status.getStatusCode());
                resp.setMensaje("Error: el cliente ya existe");
        } else {
            if (DAOMongoGimnasio.clientesPost(entity)) {
                status=Response.Status.CREATED;
                resp.setStatus(status.getStatusCode());
                resp.setMensaje("Registro insertado");
            } else {
                status=Response.Status.BAD_REQUEST;
                resp.setStatus(status.getStatusCode());
                resp.setMensaje("Error al insertar registro");
            }
        }

        response = Response
                    .status(status)
                    .entity(resp)    
                    .build();
        
        
        return response;
    }    

}
