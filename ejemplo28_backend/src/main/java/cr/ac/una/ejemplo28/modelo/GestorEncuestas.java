package cr.ac.una.ejemplo28.modelo;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author jeffr
 */
public class GestorEncuestas {

    private GestorEncuestas() {
        try {
            // Configuración de la base de datos
            InitialContext ctx = new InitialContext();
            bd = (DataSource) ctx.lookup("jdbc/EncuestasDB");
            System.out.println("Usando JNDI para acceder a la base de datos..");

        } catch (NamingException | NullPointerException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());

            bd = new MysqlDataSource();
            MysqlDataSource mds = (MysqlDataSource) bd;
            mds.setServerName("localhost");
            mds.setPortNumber(3306);
            mds.setDatabaseName("EncuestasDB");
            mds.setUser("root");
            mds.setPassword("root");

            System.out.println("Usando el manejador JDBC para acceder a la base de datos..");
        }

        try {
            String url = null;
            if (bd != null) {
                url = bd.getConnection().getMetaData().getURL();
                System.out.printf("Origen de datos: %s%n", url);
            } else {
                System.err.println("No se pudo establecer el origen de datos.");
            }
            System.out.println();

            DataSourceConnectionSource connectionSource
                    = new DataSourceConnectionSource(bd, url);
            encuestaDAO = DaoManager.createDao(connectionSource, Encuesta.class);
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    public static GestorEncuestas obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorEncuestas();
        }
        return instancia;
    }

    public int agregar(Encuesta nuevaEncuesta) throws SQLException {
        return encuestaDAO.create(nuevaEncuesta);
    }

    public Encuesta recuperar(int id) throws SQLException {
        return encuestaDAO.queryForId(id);
    }

    public int actualizar(Encuesta encuesta) throws SQLException {
        return encuestaDAO.update(encuesta);
    }

    public int eliminar(int id) throws SQLException {
        return encuestaDAO.deleteById(id);
    }

    public List<Encuesta> listarTodos() throws SQLException {
        return encuestaDAO.queryForAll();
    }

    public void actualizar() {
        encuestas.clear();
        try {
            encuestas.addAll(listarTodos());
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        actualizar();
        for (Encuesta e : encuestas) {
            result.append(String.format("\n\t%s,", e));
        }
        result.append("\n}");
        return result.toString();
    }

    private static GestorEncuestas instancia = null;

    private DataSource bd = null;
    private Dao<Encuesta, Integer> encuestaDAO;

    @XmlElementWrapper(name = "encuestas")
    @XmlElement(name = "encuesta")
    private List<Encuesta> encuestas = new ArrayList<>();
    
    public String obtenerNombreEncuesta(int encuestaId) throws SQLException {
    Encuesta encuesta = recuperar(encuestaId);
    return (encuesta != null) ? encuesta.getNombre() : "";
}

}