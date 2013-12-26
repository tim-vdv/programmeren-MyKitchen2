/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package json;

import domain.Ingredient;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author timvdv
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
    public class IngredientWriter implements MessageBodyWriter<Ingredient>{
        
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {
        return Ingredient.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Ingredient ingredient, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {
        return -1;
    }

    @Override
    public void writeTo(Ingredient ingredient, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException
    {
        JsonObjectBuilder jsonMessage = Json.createObjectBuilder();
        
        jsonMessage.add("id", ingredient.getId());
        jsonMessage.add("naam", ingredient.getNaam());
        
        System.out.println("test");
        
        try (JsonWriter writer = Json.createWriter(entityStream)) {
            writer.writeObject(jsonMessage.build());
        }
    } 
    }
    

