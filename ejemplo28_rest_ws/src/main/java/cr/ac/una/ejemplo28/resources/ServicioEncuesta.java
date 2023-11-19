/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.resources;

import cr.ac.una.ejemplo28.modelo.Encuesta;
import cr.ac.una.ejemplo28.modelo.GestorEncuestas;
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

/**
 *
 * @author jeffr
 */
@Path("/encuestas")
@RequestScoped
public class ServicioEncuesta {

    private final EncuestaRsrc encuestas;
    private GestorEncuestas gestorEncuestas;

    public ServicioEncuesta() throws SQLException {
        encuestas = new EncuestaRsrc();
        gestorEncuestas = GestorEncuestas.obtenerInstancia();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas() {
        try {
            return buildResponse("ok", encuestas.listarTodas());
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(Encuesta encuesta) {
        try {
            return buildResponse("ok", encuestas.agregar(encuesta));
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar(@PathParam("id") int encuestaId) {
        try {
            return buildResponse("ok", encuestas.recuperar(encuestaId));
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

    @GET
    @Path("/nombre/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response obtenerNombreEncuesta(@PathParam("id") int encuestaId) {
        try {
            System.out.println("Llamando a obtenerNombreEncuesta con id: " + encuestaId);
            String nombre = gestorEncuestas.obtenerNombreEncuesta(encuestaId);
            System.out.println("Nombre de la encuesta: " + nombre);
            return buildResponse("ok", nombre);
        } catch (SQLException ex) {
            return buildResponse(ex.getMessage(), null);
        }
    }

    @Context
    private UriInfo context;

}
