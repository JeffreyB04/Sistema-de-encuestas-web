package cr.ac.una.estudiantes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cr.ac.una.estudiantes.entidades.GestorEstudiantes;
import cr.ac.una.util.conversion.json.SqlDateTypeAdapter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.sql.SQLException;

public class EjemploEstudiantes {

    public static void main(String[] args) {
        new EjemploEstudiantes().test();
    }

    public void test() {
        GestorEstudiantes estudiantes = GestorEstudiantes.obtenerInstancia();
        System.out.println(estudiantes);
        System.out.println();

        try {
            estudiantes.actualizar();
            JAXBContext ctx = JAXBContext.newInstance(GestorEstudiantes.class);
            Marshaller mrs = ctx.createMarshaller();
            mrs.setProperty(Marshaller.JAXB_ENCODING, UTF_8.toString());
            mrs.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mrs.marshal(estudiantes, new FileOutputStream("../estudiantes.xml"));
            System.out.println();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(java.sql.Date.class, new SqlDateTypeAdapter())
                    .setPrettyPrinting()
                    .create();
            System.out.println(gson.toJson(estudiantes.listarTodos()));
            System.out.println();

        } catch (JAXBException | FileNotFoundException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

}
