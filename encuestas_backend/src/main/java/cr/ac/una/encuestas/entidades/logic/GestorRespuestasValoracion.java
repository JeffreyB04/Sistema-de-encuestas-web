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
public class GestorRespuestasValoracion {
    private static GestorRespuestasValoracion instancia = null;

    private DataSource bd = null;
    private Dao<RespuestaValoracion, Integer> respuestasValoracionDAO;

    @XmlElementWrapper(name = "respuestas_valoracion")
    @XmlElement(name = "respuesta_valoracion")
    private List<RespuestaValoracion> respuestasValoracion = new ArrayList<>();

    private GestorRespuestasValoracion() {
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

    public static GestorRespuestasValoracion obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorRespuestasValoracion();
        }
        return instancia;
    }

    public int agregar(RespuestaValoracion nuevaRespuesta) throws SQLException {
        return respuestasValoracionDAO.create(nuevaRespuesta);
    }

    public RespuestaValoracion recuperar(int id) throws SQLException {
        return respuestasValoracionDAO.queryForId(id);
    }

    public int actualizar(RespuestaValoracion respuestaValoracion) throws SQLException {
        return respuestasValoracionDAO.update(respuestaValoracion);
    }

    public int eliminar(int id) throws SQLException {
        return respuestasValoracionDAO.deleteById(id);
    }

    public List<RespuestaValoracion> listarTodos() throws SQLException {
        return respuestasValoracionDAO.queryForAll();
    }

    public void actualizar() {
        respuestasValoracion.clear();
        try {
            respuestasValoracion.addAll(listarTodos());
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        actualizar();
        for (RespuestaValoracion rv : respuestasValoracion) {
            result.append(String.format("\n\t%s,", rv));
        }
        result.append("\n}");
        return result.toString();
    }
}
