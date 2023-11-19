package cr.ac.una.ejemplo28.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import cr.ac.una.ejemplo28.modelo.GestorItems;
import cr.ac.una.ejemplo28.modelo.Item;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;

public class ItemRsrc {

    public ItemRsrc() throws SQLException {
        this.g = GestorItems.obtenerInstancia();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String listarTodos() throws SQLException {
        return gson.toJson(g.listarTodos());
    }

    public String agregar(String json) throws SQLException {
        return gson.toJson(g.agregar(gson.fromJson(json, Item.class)));
    }

    public String recuperar(String itemId) throws SQLException {
        return gson.toJson(g.recuperar(itemId));
    }

    public String actualizar(String json) throws SQLException {
        return gson.toJson(g.actualizar(gson.fromJson(json, Item.class)));
    }

    public String eliminar(String itemId) throws IOException, SQLException {
        StringWriter sw = new StringWriter();
        JsonWriter jw = new JsonWriter(sw);

        jw.beginObject()
                .name("id").value(g.eliminar(itemId))
                .endObject();

        return sw.toString();
    }

    private final GestorItems g;
    private final Gson gson;
}
