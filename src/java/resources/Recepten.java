package resources;

import domain.Ingredient;
import domain.Recept;
import domain.ReceptStap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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
    
    private long receptId;
    //private String receptNaam;
    private String receptBeschrijving;
    private String receptExtraMateriaal;
    private String beschrijving;

    //private image afbeelding;
    public long getReceptId() {
        return receptId;
    }

    public void setReceptId(long receptId) {
        this.receptId = receptId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recept> getAllRecepten(@QueryParam("start")@DefaultValue("0")int start,@QueryParam("results")@DefaultValue("10")int results){
        TypedQuery<Recept> query =  em.createNamedQuery("Recept.findAll", Recept.class);
        query.setFirstResult(start);
        query.setMaxResults(results);
        return query.getResultList();
    }
    
    @GET
    @Path("{receptid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecept(@PathParam("receptid") long id)
    {
        Recept recept = em.find(Recept.class, id);

        return Response.ok(recept).build();
    }

    public String getReceptBeschrijving() {
        return receptBeschrijving;
    }

    public void setReceptBeschrijving(String receptBeschrijving) {
        this.receptBeschrijving = receptBeschrijving;
    }

    public String getReceptExtraMateriaal() {
        return receptExtraMateriaal;
    }

    public void setReceptExtraMateriaal(String receptExtraMateriaal) {
        this.receptExtraMateriaal = receptExtraMateriaal;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
    
    
}
