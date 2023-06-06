package ruan.nunes;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.net.URI;
import java.text.MessageFormat;

@Path("/my-entity")
public class MyEntityResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getId(@PathParam("id") Long id) {
        return Response.ok(MyEntity.findById(id)).build();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createMyEntity(@RequestBody MyEntity entity){
        entity.persist();
        return Response.created(URI.create(MessageFormat.format("/my-entity/{0}", entity.id))).build();
    }
}
