package ru.ebt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class LightAppController {

    private ConcurrentMap<String, Boolean> resource = new ConcurrentHashMap<>();

    @GET
    public Map getAll() {
        return resource;
    }

    @GET
    @Path("/{resource}")
    public Boolean get(@PathParam("resource") String r) {
        return resource.get(r);
    }

    @PUT
    @Path("/{resource}")
    public Boolean put(@PathParam("resource") String r, Boolean status) {
        if (resource.containsKey(r)) {
            resource.put(r, status);
            return status;
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @POST
    @Path("/{resource}")
    public Boolean post(@PathParam("resource") String r, Boolean status) throws JsonProcessingException {
        resource.put(r, status);
        return status;
    }

    @DELETE
    @Path("/{resource}")
    public void delete(@PathParam("resource") String r) {
        resource.remove(r);
    }


}
