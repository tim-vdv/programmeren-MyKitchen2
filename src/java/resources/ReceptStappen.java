package resources;

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
public class ReceptStappen { 
    
    @PersistenceContext
    private EntityManager em;
    
    private long receptId;
    private long ingredientId;
    private long informatieId;
    private long hoeveelheid;

    public long getReceptId() {
        return receptId;
    }

    public void setReceptId(long receptId) {
        this.receptId = receptId;
    }

    @GET
    @Path("/{receptid}/receptstappen")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptStap> getAllReceptStappen(@PathParam("receptid")int receptid){
        TypedQuery<ReceptStap> query =  em.createNamedQuery("ReceptStap.findById", ReceptStap.class);
        query.setParameter("receptid", receptid);
        return query.getResultList();
    }
    
    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public long getInformatieId() {
        return informatieId;
    }

    public void setInformatieId(long informatieId) {
        this.informatieId = informatieId;
    }

    public long getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(long hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }
    
    
}
