import domain.Recept;
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
public class ReceptReader implements MessageBodyReader<Recept>{

    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Recept.class.isAssignableFrom(type);
    }
    
    public Recept readFrom(Class<Recept> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        JsonReader reader = Json.createReader(entityStream);
        JsonObject jsonMessage = reader.readObject();
        
        Recept recept = new Recept();
        
        recept.setNaam(jsonMessage.getString("naam"));
        return recept;
    }
    
}