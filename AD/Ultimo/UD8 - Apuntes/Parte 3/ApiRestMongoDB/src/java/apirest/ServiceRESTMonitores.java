package apirest;

import clases.Monitores;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("monitores")
public class ServiceRESTMonitores {

    @Context
    private UriInfo context;

    public ServiceRESTMonitores() {
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Monitores> getAll() {
        return DAOMongoGimnasio.monitoresGetAll();
    }

    @GET
    @Path("{monitor}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Monitores getOne(@PathParam("monitor") String idMonitor) {
        return DAOMongoGimnasio.monitoresGet(idMonitor);
    }
    
    

}
