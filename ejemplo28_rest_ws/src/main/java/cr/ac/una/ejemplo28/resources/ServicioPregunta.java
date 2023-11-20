/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.resources;

import cr.ac.una.ejemplo28.modelo.GestorPreguntas;
import cr.ac.una.ejemplo28.modelo.Pregunta;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
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
import java.util.List;

@Path("/preguntas")
@RequestScoped
public class ServicioPregunta {

    private final PreguntaRsrc preguntas;
    private GestorPreguntas gestorPreguntas;

    public ServicioPregunta() throws SQLException {
        preguntas = new PreguntaRsrc();
        gestorPreguntas = GestorPreguntas.obtenerInstancia();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas() {
        try {
            return buildResponse("ok", preguntas.listarTodas());
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(Pregunta pregunta) {
        try {
            return buildResponse("ok", preguntas.agregar(pregunta));
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar(@PathParam("id") int preguntaId) {
        try {
            return buildResponse("ok", preguntas.recuperar(preguntaId));
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
