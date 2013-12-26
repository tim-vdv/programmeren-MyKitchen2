import domain.ReceptStap;
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
public class ReceptStapReader implements MessageBodyReader<ReceptStap>{

    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return ReceptStap.class.isAssignableFrom(type);
    }
    
    public ReceptStap readFrom(Class<ReceptStap> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        JsonReader reader = Json.createReader(entityStream);
        JsonObject jsonMessage = reader.readObject();
        
        ReceptStap receptStap = new ReceptStap();
        
        receptStap.setHoeveelheid(jsonMessage.getInt("hoeveelheid"));
        receptStap.setIngredientId(jsonMessage.getInt("ingredientId"));
        receptStap.setReceptId(jsonMessage.getInt("receptId"));
        return receptStap;
    }
    
}
