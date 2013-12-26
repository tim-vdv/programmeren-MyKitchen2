package json;

import domain.Gebruiker;
import domain.Ingredient;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class GebruikerReader implements MessageBodyReader<Gebruiker>{

    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Ingredient.class.isAssignableFrom(type);
    }
    
    public Gebruiker readFrom(Class<Gebruiker> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        JsonReader reader = Json.createReader(entityStream);
        JsonObject jsonMessage = reader.readObject();
        
        
        Gebruiker gebruiker = new Gebruiker();

        gebruiker.setId(gebruiker.getId());
        gebruiker.setEmail(jsonMessage.getString("email"));
        gebruiker.setWachtwoord(jsonMessage.getString("wachtwoord"));
        gebruiker.setFavorieten(jsonMessage.getString("favorieten"));
        gebruiker.setKoelkast(jsonMessage.getString("koelkast"));
        return gebruiker;
        
    }
    
}