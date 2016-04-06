package ru.ebt;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/ui")
public class UiApplicationController {

    @GET
    @Path("/{static}")
    public Response page(@PathParam("static") String sttc) {
        if (!sttc.startsWith("/")) {
            sttc = "/" + sttc;
        }
        return Response.ok(this.getClass().getResourceAsStream(sttc)).build();
    }

}
