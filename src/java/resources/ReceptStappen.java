package resources;

import domain.Ingredient;
import domain.ReceptStap;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("receptstappen")
@Transactional
public class ReceptStappen { 
    
    @PersistenceContext
    private EntityManager em;

    @GET
    @Path("{receptStapId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptStap> getReceptStap(@PathParam("receptStapId")int receptstapid){
        TypedQuery<ReceptStap> query =  em.createNamedQuery("ReceptStap.findById1", ReceptStap.class);
        query.setParameter("receptStapId", receptstapid);
        return query.getResultList();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReceptStap(ReceptStap receptstap)
    {
        em.persist(receptstap);
        System.out.println("post: " +receptstap);
        return Response.status(Response.Status.CREATED).location(URI.create("/" + receptstap.getReceptId())).build();
    }
    
    @PUT
    @Path("{receptStapId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReceptStap(@PathParam("receptStapId") long id, ReceptStap receptstapUpdate)
    {
        ReceptStap receptstap = em.find(ReceptStap.class, id);

        if (receptstap == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        em.detach(receptstap);
        
       
        receptstap.setHoeveelheid(receptstapUpdate.getHoeveelheid());
        receptstap.setIngredientId(receptstapUpdate.getIngredientId());
        receptstap.setReceptId(receptstapUpdate.getReceptId());
        
        
        em.merge(receptstap);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptStap> getReceptStappen(){
        TypedQuery<ReceptStap> query =  em.createNamedQuery("ReceptStap.findAll", ReceptStap.class);
        return query.getResultList();
    }
    
    @DELETE
    @Path("{receptStapId}")
    public Response deleteIngredient(@PathParam("receptStapId") long id)
    {
        ReceptStap receptstap = em.find(ReceptStap.class, id);

        em.remove(receptstap);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}
