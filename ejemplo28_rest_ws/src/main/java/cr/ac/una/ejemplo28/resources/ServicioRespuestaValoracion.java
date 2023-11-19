/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.resources;

import cr.ac.una.ejemplo28.modelo.RespuestaValoracion;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.sql.SQLException;

@Path("/respuestasvaloracion")
@RequestScoped
public class ServicioRespuestaValoracion {

    private final RespuestaValoracionRsrc respuestasValoracion;

    public ServicioRespuestaValoracion() throws SQLException {
        respuestasValoracion = new RespuestaValoracionRsrc();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas() {
        try {
            return buildResponse("ok", respuestasValoracion.listarTodas());
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(RespuestaValoracion respuestaValoracion) {
        try {
            return buildResponse("ok", respuestasValoracion.agregar(respuestaValoracion));
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar(@PathParam("id") int respuestaValoracionId) {
        try {
            return buildResponse("ok", respuestasValoracion.recuperar(respuestaValoracionId));
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    private Response buildResponse(String status, Object data) {
        return Response
                .ok(status)
                .entity(data)
                .build();
    }

    @Context
    private UriInfo context;
}
