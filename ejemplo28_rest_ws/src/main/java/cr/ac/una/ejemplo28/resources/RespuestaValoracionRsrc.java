/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cr.ac.una.ejemplo28.modelo.GestorRespuestasValoracion;
import cr.ac.una.ejemplo28.modelo.RespuestaValoracion;

import java.sql.SQLException;
import java.util.List;

public class RespuestaValoracionRsrc {

    private final GestorRespuestasValoracion gestorRespuestasValoracion;
    private final Gson gson;

    public RespuestaValoracionRsrc() throws SQLException {
        this.gestorRespuestasValoracion = GestorRespuestasValoracion.obtenerInstancia();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String listarTodas() throws SQLException {
        List<RespuestaValoracion> respuestasValoracion = gestorRespuestasValoracion.listarTodos();
        return gson.toJson(respuestasValoracion);
    }

    public String agregar(RespuestaValoracion respuestaValoracion) throws SQLException {
        return gson.toJson(gestorRespuestasValoracion.agregar(respuestaValoracion));
    }

    public String recuperar(int respuestaValoracionId) throws SQLException {
        RespuestaValoracion respuestaValoracion = gestorRespuestasValoracion.recuperar(respuestaValoracionId);
        return gson.toJson(respuestaValoracion);
    }
}
