package resources;

import domain.Gebruiker;
import domain.Ingredient;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("gebruikers")
@Transactional
public class Gebruikers {
    
    @PersistenceContext
    private EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gebruiker> getAllGebruikers(){
        TypedQuery<Gebruiker> query =  em.createNamedQuery("Gebruiker.findAll", Gebruiker.class);
        return query.getResultList();
    }
    
    @GET
    @Path("{gebruikerid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGebruiker(@PathParam("gebruikerid") long id)
    {
        Gebruiker gebruiker = em.find(Gebruiker.class, id);

        return Response.ok(gebruiker).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGebruiker(Gebruiker gebruiker)
    {
        em.persist(gebruiker);
        return Response.status(Response.Status.CREATED).location(URI.create("/" + gebruiker.getId())).build();
    }
    
    @PUT
    @Path("{gebruikerid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGebruiker(@PathParam("gebruikerid") long id, Gebruiker gebruikerUpdate)
    {
        Gebruiker gebruiker = em.find(Gebruiker.class, id);

        if (gebruiker == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        em.detach(gebruiker);
        
        
        gebruiker.setEmail(gebruikerUpdate.getEmail());
        gebruiker.setWachtwoord(gebruikerUpdate.getWachtwoord());
        gebruiker.setFavorieten(gebruikerUpdate.getFavorieten());
        gebruiker.setKoelkast(gebruikerUpdate.getKoelkast());
        
        
        em.merge(gebruiker);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("{gebruikerid}")
    public Response deleteGebruiker(@PathParam("gebruikerid") long id)
    {
        Gebruiker gebruiker = em.find(Gebruiker.class, id);

        em.remove(gebruiker);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
