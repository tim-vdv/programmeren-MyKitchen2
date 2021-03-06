package resources;

import domain.Ingredient;
import java.net.URI;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
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
import javax.ws.rs.core.Response.Status;

@Path("ingredienten")
@Transactional
public class Ingredienten {
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private Validator validator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingredient> getAllIngredienten(){
        TypedQuery<Ingredient> query =  em.createNamedQuery("Ingredient.findAll", Ingredient.class);
        return query.getResultList();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIngredient(Ingredient ingredient)
    {
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);
        if (!violations.isEmpty()) {
            
            // Verzamel de foutberichten uit alle ConstraintViolations in één foutbericht.
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Ingredient> violation : violations) {
                if (errorMessage.length() != 0) {
                    errorMessage.append(", ");
                }
                errorMessage.append(violation.getMessage());
            }
            errorMessage.append(".");
            
            return Response.status(Status.BAD_REQUEST).entity("Kan ingredient niet toevoegen: " + errorMessage).build();
        }
        
        em.persist(ingredient);
        return Response.status(Response.Status.CREATED).location(URI.create("/" + ingredient.getId())).build();
    }
    
    @PUT
    @Path("{ingredientid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredient(@PathParam("ingredientid") long id, Ingredient ingredientUpdate)
    {
        Ingredient ingredient = em.find(Ingredient.class, id);

        if (ingredient == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        em.detach(ingredient);
        
        if (ingredientUpdate.getNaam() != null) {
            ingredient.setNaam(ingredientUpdate.getNaam());
        }
        
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);
        if (!violations.isEmpty()) {
            
            // Verzamel de foutberichten uit alle ConstraintViolations in één foutbericht.
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Ingredient> violation : violations) {
                if (errorMessage.length() != 0) {
                    errorMessage.append(", ");
                }
                errorMessage.append(violation.getMessage());
            }
            errorMessage.append(".");
            
            return Response.status(Status.BAD_REQUEST).entity("Kan ingredient niet wijzigen: " + errorMessage).build();
        }
        
        em.merge(ingredient);
        
        return Response.status(Status.NO_CONTENT).build();
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
    public Response deleteIngredient(@PathParam("ingredientid") long id)
    {
        Ingredient ingredient = em.find(Ingredient.class, id);

        em.remove(ingredient);
        
        return Response.status(Status.NO_CONTENT).build();
    }
    
}
