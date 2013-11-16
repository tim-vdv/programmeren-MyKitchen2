package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name ="Recept.findAll", query = "SELECT m FROM Recept m"),
    @NamedQuery(name = "Recept.findById", query = "SELECT e FROM Recept e WHERE e.id = :id"),
  })

public class Recept {
    @Id
    @GeneratedValue
    private long id;  
    private String Naam;
    private String receptBeschrijving;
    private String ExtraMateriaal;
    private String kookBeschrijving;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return Naam;
    }

    public void setNaam(String Naam) {
        this.Naam = Naam;
    }

    public String getReceptBeschrijving() {
        return receptBeschrijving;
    }

    public void setReceptBeschrijving(String receptBeschrijving) {
        this.receptBeschrijving = receptBeschrijving;
    }

    public String getExtraMateriaal() {
        return ExtraMateriaal;
    }

    public void setExtraMateriaal(String ExtraMateriaal) {
        this.ExtraMateriaal = ExtraMateriaal;
    }

    public String getKookBeschrijving() {
        return kookBeschrijving;
    }

    public void setKookBeschrijving(String kookBeschrijving) {
        this.kookBeschrijving = kookBeschrijving;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Recept other = (Recept) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
}
