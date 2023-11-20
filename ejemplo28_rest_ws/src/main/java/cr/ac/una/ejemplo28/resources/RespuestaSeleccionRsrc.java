/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cr.ac.una.ejemplo28.modelo.GestorRespuestasSeleccion;
import cr.ac.una.ejemplo28.modelo.RespuestaSeleccion;
import java.sql.SQLException;
import java.util.List;

public class RespuestaSeleccionRsrc {

    private final GestorRespuestasSeleccion gestorRespuestasSeleccion;
    private final Gson gson;

    public RespuestaSeleccionRsrc() throws SQLException {
        this.gestorRespuestasSeleccion = GestorRespuestasSeleccion.obtenerInstancia();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String listarTodas() throws SQLException {
        List<RespuestaSeleccion> respuestasSeleccion = gestorRespuestasSeleccion.listarTodos();
        return gson.toJson(respuestasSeleccion);
    }

    public String agregar(RespuestaSeleccion respuestaSeleccion) throws SQLException {
        return gson.toJson(gestorRespuestasSeleccion.agregar(respuestaSeleccion));
    }

    public String recuperar(int respuestaSeleccionId) throws SQLException {
        RespuestaSeleccion respuestaSeleccion = gestorRespuestasSeleccion.recuperar(respuestaSeleccionId);
        return gson.toJson(respuestaSeleccion);
    }
}
