/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cr.ac.una.ejemplo28.modelo.GestorPreguntas;
import cr.ac.una.ejemplo28.modelo.Pregunta;
import java.sql.SQLException;
import java.util.List;

public class PreguntaRsrc {

    private final GestorPreguntas gestorPreguntas;
    private final Gson gson;

    public PreguntaRsrc() throws SQLException {
        this.gestorPreguntas = GestorPreguntas.obtenerInstancia();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String listarTodas() throws SQLException {
        List<Pregunta> preguntas = gestorPreguntas.listarTodos();
        return gson.toJson(preguntas);
    }

    public String agregar(Pregunta pregunta) throws SQLException {
        return gson.toJson(gestorPreguntas.agregar(pregunta));
    }

    public String recuperar(int preguntaId) throws SQLException {
        Pregunta pregunta = gestorPreguntas.recuperar(preguntaId);
        return gson.toJson(pregunta);
    }
}
