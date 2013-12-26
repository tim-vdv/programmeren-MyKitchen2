package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import static javax.swing.text.StyleConstants.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name ="Ingredient.findAll", query = "SELECT m FROM Ingredient m"),
    @NamedQuery(name = "Ingredient.findById", query = "SELECT e FROM Ingredient e WHERE e.id = :id"),
  })
public class Ingredient {
    
    @Id @GeneratedValue
    @Min(value = 0, message = "een ingredient moet een positief id hebben")
    private long id;
    
    @NotNull(message = "een ingredient moet een niet lege naam hebben")
    @Size(min = 1, message = "een ingredient moet een niet lege naam hebben")
    private String naam;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Ingredient other = (Ingredient) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
