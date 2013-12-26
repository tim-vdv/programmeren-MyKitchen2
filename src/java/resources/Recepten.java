package resources;

import domain.Gebruiker;
import domain.Ingredient;
import domain.Recept;
import domain.ReceptStap;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("recepten")
@Transactional
public class Recepten {
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private Validator validator;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recept> getAllRecepten(){
        TypedQuery<Recept> query =  em.createNamedQuery("Recept.findAll", Recept.class);
        return query.getResultList();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecept(Recept recept)
    {
        Set<ConstraintViolation<Recept>> violations = validator.validate(recept);
        if (!violations.isEmpty()) {
            
            // Verzamel de foutberichten uit alle ConstraintViolations in één foutbericht.
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Recept> violation : violations) {
                if (errorMessage.length() != 0) {
                    errorMessage.append(", ");
                }
                errorMessage.append(violation.getMessage());
            }
            errorMessage.append(".");
            
            return Response.status(Response.Status.BAD_REQUEST).entity("Kan recept niet toevoegen: " + errorMessage).build();
        }
        
        em.persist(recept);
        System.out.println("post: " +recept);
        return Response.status(Response.Status.CREATED).location(URI.create("/" + recept.getId())).build();
    }
    
    @PUT
    @Path("{receptid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredient(@PathParam("receptid") long id, Recept receptUpdate)
    {
        Recept recept = em.find(Recept.class, id);

        if (recept == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        em.detach(recept);
        
        if (receptUpdate.getNaam() != null) {
            recept.setNaam(receptUpdate.getNaam());
        }
        
        if (receptUpdate.getKookBeschrijving() != null) {
            recept.setKookBeschrijving(receptUpdate.getKookBeschrijving());
        }
        
        if (receptUpdate.getReceptBeschrijving() != null) {
            recept.setReceptBeschrijving(receptUpdate.getReceptBeschrijving());
        }
        
        
        Set<ConstraintViolation<Recept>> violations = validator.validate(recept);
        if (!violations.isEmpty()) {
            
            // Verzamel de foutberichten uit alle ConstraintViolations in één foutbericht.
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Recept> violation : violations) {
                if (errorMessage.length() != 0) {
                    errorMessage.append(", ");
                }
                errorMessage.append(violation.getMessage());
            }
            errorMessage.append(".");
            
            return Response.status(Response.Status.BAD_REQUEST).entity("Kan recept niet wijzigen: " + errorMessage).build();
        }
        
        em.merge(recept);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("{receptid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecept(@PathParam("receptid") long id)
    {
        Recept recept = em.find(Recept.class, id);

        return Response.ok(recept).build();
    }
    
    @DELETE
    @Path("{receptid}")
    public Response deleteIngredient(@PathParam("receptid") long id)
    {
        Recept recept = em.find(Recept.class, id);

        em.remove(recept);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("/{receptid}/receptstappen")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptStap> getAllReceptStappen(@PathParam("receptid")int receptid){
        TypedQuery<ReceptStap> query =  em.createNamedQuery("ReceptStap.findById", ReceptStap.class);
        query.setParameter("receptid", receptid);
        return query.getResultList();
    }
    
}
