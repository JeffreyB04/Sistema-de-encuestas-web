/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ejemplo28.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cr.ac.una.ejemplo28.modelo.Encuesta;
import cr.ac.una.ejemplo28.modelo.GestorEncuestas;
import java.sql.SQLException;
import java.util.List;

public class EncuestaRsrc {

    private final GestorEncuestas gestorEncuestas;
    private final Gson gson;

    public EncuestaRsrc() throws SQLException {
        this.gestorEncuestas = GestorEncuestas.obtenerInstancia();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String listarTodas() throws SQLException {
        List<Encuesta> encuestas = gestorEncuestas.listarTodos();
        return gson.toJson(encuestas);
    }

    public String agregar(Encuesta encuesta) throws SQLException {
        return gson.toJson(gestorEncuestas.agregar(encuesta));
    }

    public String recuperar(int encuestaId) throws SQLException {
        Encuesta encuesta = gestorEncuestas.recuperar(encuestaId);
        return gson.toJson(encuesta);
    }

}
