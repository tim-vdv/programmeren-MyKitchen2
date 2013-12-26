package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name ="Gebruiker.findAll", query = "SELECT m FROM Gebruiker m"),
    @NamedQuery(name = "Gebruiker.findById", query = "SELECT e FROM Gebruiker e WHERE e.id = :id"),
  })
public class Gebruiker {
    
    @Id @GeneratedValue
    @Min(value = 0, message = "een gebruiker moet een positief id hebben")
    private long id;
    
    @NotNull(message = "een gebruiker moet een niet lege email hebben")
    @Size(min = 1, message = "een gebruiker moet een niet lege email hebben")
    private String email;
    
    @NotNull(message = "een gebruiker moet een niet lege wachtwoord hebben")
    @Size(min = 1, message = "een gebruiker moet een niet lege wachtwoord hebben")
    private String wachtwoord;
    
    private String koelkast;
    private String favorieten;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getKoelkast() {
        return koelkast;
    }

    public void setKoelkast(String koelkast) {
        this.koelkast = koelkast;
    }

    public String getFavorieten() {
        return favorieten;
    }

    public void setFavorieten(String favorieten) {
        this.favorieten = favorieten;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Gebruiker other = (Gebruiker) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
