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
public class GestorRespuestasAbiertas {
    private static GestorRespuestasAbiertas instancia = null;

    private DataSource bd = null;
    private Dao<RespuestaAbierta, Integer> respuestasAbiertasDAO;

    @XmlElementWrapper(name = "respuestas_abiertas")
    @XmlElement(name = "respuesta_abierta")
    private List<RespuestaAbierta> respuestasAbiertas = new ArrayList<>();

    private GestorRespuestasAbiertas() {
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

    public static GestorRespuestasAbiertas obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorRespuestasAbiertas();
        }
        return instancia;
    }

    public int agregar(RespuestaAbierta nuevaRespuesta) throws SQLException {
        return respuestasAbiertasDAO.create(nuevaRespuesta);
    }

    public RespuestaAbierta recuperar(int id) throws SQLException {
        return respuestasAbiertasDAO.queryForId(id);
    }

    public int actualizar(RespuestaAbierta respuestaAbierta) throws SQLException {
        return respuestasAbiertasDAO.update(respuestaAbierta);
    }

    public int eliminar(int id) throws SQLException {
        return respuestasAbiertasDAO.deleteById(id);
    }

    public List<RespuestaAbierta> listarTodos() throws SQLException {
        return respuestasAbiertasDAO.queryForAll();
    }

    public void actualizar() {
        respuestasAbiertas.clear();
        try {
            respuestasAbiertas.addAll(listarTodos());
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        actualizar();
        for (RespuestaAbierta ra : respuestasAbiertas) {
            result.append(String.format("\n\t%s,", ra));
        }
        result.append("\n}");
        return result.toString();
    }
}
