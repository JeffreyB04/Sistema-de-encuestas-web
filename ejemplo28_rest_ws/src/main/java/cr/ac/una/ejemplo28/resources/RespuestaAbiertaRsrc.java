/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cr.ac.una.ejemplo28.modelo.GestorRespuestasAbiertas;
import cr.ac.una.ejemplo28.modelo.RespuestaAbierta;
import java.sql.SQLException;
import java.util.List;

public class RespuestaAbiertaRsrc {

    private final GestorRespuestasAbiertas gestorRespuestasAbiertas;
    private final Gson gson;

    public RespuestaAbiertaRsrc() throws SQLException {
        this.gestorRespuestasAbiertas = GestorRespuestasAbiertas.obtenerInstancia();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String listarTodas() throws SQLException {
        List<RespuestaAbierta> respuestasAbiertas = gestorRespuestasAbiertas.listarTodos();
        return gson.toJson(respuestasAbiertas);
    }

    public String agregar(RespuestaAbierta respuestaAbierta) throws SQLException {
        return gson.toJson(gestorRespuestasAbiertas.agregar(respuestaAbierta));
    }

    public String recuperar(int respuestaAbiertaId) throws SQLException {
        RespuestaAbierta respuestaAbierta = gestorRespuestasAbiertas.recuperar(respuestaAbiertaId);
        return gson.toJson(respuestaAbierta);
    }
}
