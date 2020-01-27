package servicerest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("aritmetica")
public class MathResource {

    @Context
    private UriInfo context;


    public MathResource() {
    }


    @GET
    @Path("/sumar/{operando1}/{operando2}")
    @Produces(MediaType.TEXT_PLAIN)
    public int sumar(@PathParam("operando1") int op1, @PathParam("operando2") int op2) {
        return op1+op2;
    }



}
