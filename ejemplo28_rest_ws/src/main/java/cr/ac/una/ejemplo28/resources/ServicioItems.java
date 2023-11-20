package cr.ac.una.ejemplo28.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.io.IOException;
import java.sql.SQLException;

@Path("/items")
@RequestScoped
public class ServicioItems {

    public ServicioItems() throws SQLException {
        items = new ItemRsrc();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        try {
            return buildResponse("ok", items.listarTodos());
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @POST
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(String json) {
        try {
            return buildResponse("ok", items.agregar(json));
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar(@PathParam("id") String itemId) {
        try {
            return buildResponse("ok", items.recuperar(itemId));
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @PUT
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(String json) {
        try {
            return buildResponse("ok", items.actualizar(json));
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") String itemId) {
        try {
            return buildResponse("ok", items.eliminar(itemId));
        } catch (IOException | SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    // https://javascript.info/fetch-crossorigin
    // https://www.baeldung.com/cors-in-jax-rs
    //
    private Response buildResponse(String status, Object data) {
        return Response
                .ok(status)
                .entity(data)
                .build();
    }

    private final ItemRsrc items;
    @Context
    private UriInfo context;
}
