/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.encuestas.entidades.logic;
import cr.ac.una.encuestas.entidades.logic.Encuesta;
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
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author jeffr
 */
public class GestorRespuestasSeleccion {
      private static GestorRespuestasSeleccion instancia = null;

    private DataSource bd = null;
    private Dao<RespuestaSeleccion, Integer> respuestasSeleccionDAO;

    @XmlElementWrapper(name = "respuestas_seleccion")
    @XmlElement(name = "respuesta_seleccion")
    private List<RespuestaSeleccion> respuestasSeleccion = new ArrayList<>();

    private GestorRespuestasSeleccion() {
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
    }

    public static GestorRespuestasSeleccion obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorRespuestasSeleccion();
        }
        return instancia;
    }

    public int agregar(RespuestaSeleccion nuevaRespuesta) throws SQLException {
        return respuestasSeleccionDAO.create(nuevaRespuesta);
    }

    public RespuestaSeleccion recuperar(int id) throws SQLException {
        return respuestasSeleccionDAO.queryForId(id);
    }

    public int actualizar(RespuestaSeleccion respuestaSeleccion) throws SQLException {
        return respuestasSeleccionDAO.update(respuestaSeleccion);
    }

    public int eliminar(int id) throws SQLException {
        return respuestasSeleccionDAO.deleteById(id);
    }

    public List<RespuestaSeleccion> listarTodos() throws SQLException {
        return respuestasSeleccionDAO.queryForAll();
    }

    public void actualizar() {
        respuestasSeleccion.clear();
        try {
            respuestasSeleccion.addAll(listarTodos());
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        actualizar();
        for (RespuestaSeleccion rs : respuestasSeleccion) {
            result.append(String.format("\n\t%s,", rs));
        }
        result.append("\n}");
        return result.toString();
    }
}
