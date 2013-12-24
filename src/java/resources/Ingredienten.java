package resources;

import domain.Ingredient;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("ingredienten")
@Transactional
public class Ingredienten {
    
    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingredient> getAllIngredienten(@QueryParam("start")@DefaultValue("0")int start,@QueryParam("results")@DefaultValue("10")int results){
        TypedQuery<Ingredient> query =  em.createNamedQuery("Ingredient.findAll", Ingredient.class);
        query.setFirstResult(start);
        query.setMaxResults(results);
        return query.getResultList();
    }
    
    @GET
    @Path("{ingredientid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredient(@PathParam("ingredientid") long id)
    {
        Ingredient ingredient = em.find(Ingredient.class, id);

        return Response.ok(ingredient).build();
    }
    
    @DELETE
    @Path("{ingredientid}")
    public Response deleteMessage(@PathParam("ingredientid") long id)
    {
        Ingredient ingredient = em.find(Ingredient.class, id);

        em.remove(ingredient);
        
        return Response.status(Status.NO_CONTENT).build();
    }
    
}
