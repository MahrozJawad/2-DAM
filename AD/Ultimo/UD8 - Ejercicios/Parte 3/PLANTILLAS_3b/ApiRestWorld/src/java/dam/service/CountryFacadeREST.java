
package dam.service;

import dam.City;
import dam.Country;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("country")
public class CountryFacadeREST extends AbstractFacade<Country> {

    @PersistenceContext(unitName = "ApiRestWorldPU")
    private EntityManager em;

    public CountryFacadeREST() {
        super(Country.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Country entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Country entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Country find(@PathParam("id") String id) {
//        return super.find(id);
//    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Country> findAll() {
        List<Country> countries = super.findAll();
        return countries;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Country> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("{code}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Country findWithCode(@PathParam("code") String code) {
        
        CityFacadeREST citiesRest = new CityFacadeREST();
        Query qCities = citiesRest.getEntityManager().createQuery("SELECT * FROM City WHERE countrycode='" + code + "'" );
        List<City> cities = qCities.getResultList();
        
        Country country = null;
        Query qCountry = em.createQuery("SELECT * FROM Pais WHERE code='" + code + "'" );
        
        country = (Country)qCountry.getResultList().get(0);
        country.setCityCollection(cities);
        
        
        return country;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
