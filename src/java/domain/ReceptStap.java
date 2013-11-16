package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name ="Recept.findAll", query = "SELECT m FROM Recept m"),
    @NamedQuery(name = "ReceptStap.findById", query = "SELECT e FROM ReceptStap e WHERE e.receptId = :receptid"),
  })

public class ReceptStap {
    
    @Id @GeneratedValue
    private long receptStapId;
    private long receptId;
    private long ingredientId;
    private long informatieId;
    private long hoeveelheid;

    public long getReceptStapId() {
        return receptStapId;
    }

    public void setReceptStapId(long receptStapId) {
        this.receptStapId = receptStapId;
    }

    public long getReceptId() {
        return receptId;
    }

    public void setReceptId(long receptId) {
        this.receptId = receptId;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.receptId ^ (this.receptId >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReceptStap other = (ReceptStap) obj;
        if (this.receptId != other.receptId) {
            return false;
        }
        return true;
    }
    
    
}
