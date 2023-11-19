package cr.ac.una.ejemplo28.modelo;

import com.j256.ormlite.dao.Dao;
import cr.ac.una.db.Database;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "lista_items")
public class GestorItems implements Serializable {

    private GestorItems() throws SQLException {
        try {
            this.bd = Database.createMySQLDatabase("bd_items.properties.xml");
            this.itemDAO = bd.getDAO(Item.class);
        } catch (SQLException ex) {
            System.err.printf("", ex.getMessage());
            throw ex;
        }
    }

    public static GestorItems obtenerInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new GestorItems();
        }
        return instancia;
    }

    public List<Item> listarTodos() throws SQLException {
        items.clear();
        items = itemDAO.queryForAll();
        items.sort((Item o1, Item o2) -> {
            int r = o1.getId().compareTo(o2.getId());
            return r;
        });
        return items;
    }

    public int agregar(Item nuevoItem) throws SQLException {
        return itemDAO.create(nuevoItem);
    }

    public Item recuperar(String itemId) throws SQLException {
        return itemDAO.queryForId(itemId);
    }

    public int actualizar(Item item) throws SQLException {
        return itemDAO.update(item);
    }

    public int eliminar(String itemId) throws SQLException {
        return itemDAO.deleteById(itemId);
    }

    public void actualizar() {
        items.clear();
        try {
            items.addAll(listarTodos());
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        try {
            r.append(listarTodos().toString());
        } catch (SQLException ex) {
            r.append(String.format("Excepción: '%s'", ex.getMessage()));
        }
        return r.toString();
    }

    private static GestorItems instancia = null;

    private Database bd = null;
    private Dao<Item, String> itemDAO;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<Item> items = new ArrayList<>();
}
