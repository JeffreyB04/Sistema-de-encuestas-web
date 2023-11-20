/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.resources;

import cr.ac.una.ejemplo28.modelo.RespuestaAbierta;
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

@Path("/respuestasabiertas")
@RequestScoped
public class ServicioRespuestaAbierta {

    private final RespuestaAbiertaRsrc respuestasAbiertas;

    public ServicioRespuestaAbierta() throws SQLException {
        respuestasAbiertas = new RespuestaAbiertaRsrc();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas() {
        try {
            return buildResponse("ok", respuestasAbiertas.listarTodas());
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(RespuestaAbierta respuestaAbierta) {
        try {
            return buildResponse("ok", respuestasAbiertas.agregar(respuestaAbierta));
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar(@PathParam("id") int respuestaAbiertaId) {
        try {
            return buildResponse("ok", respuestasAbiertas.recuperar(respuestaAbiertaId));
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
