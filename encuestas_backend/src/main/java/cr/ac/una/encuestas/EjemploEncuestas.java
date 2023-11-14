/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.encuestas;

import cr.ac.una.encuestas.entidades.Encuesta;
import cr.ac.una.encuestas.entidades.GestorEncuestas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cr.ac.una.util.conversion.json.SqlDateTypeAdapter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

public class EjemploEncuestas {

    public static void main(String[] args) {
        new EjemploEncuestas().test();
    }

    public void test() {
        GestorEncuestas gestorEncuestas = GestorEncuestas.obtenerInstancia();
        System.out.println(gestorEncuestas);
        System.out.println();

        try {
            gestorEncuestas.actualizar();
            
            // Marshalling a XML
            JAXBContext ctx = JAXBContext.newInstance(GestorEncuestas.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(gestorEncuestas, new FileOutputStream("../encuestas.xml"));
            System.out.println("Datos de encuestas guardados en encuestas.xml");
            System.out.println();

            // Gson para JSON
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(java.sql.Date.class, new SqlDateTypeAdapter())
                    .setPrettyPrinting()
                    .create();
            List<Encuesta> listaEncuestas = gestorEncuestas.listarTodos();
            System.out.println(gson.toJson(listaEncuestas));
            System.out.println();

        } catch (JAXBException | FileNotFoundException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }
}