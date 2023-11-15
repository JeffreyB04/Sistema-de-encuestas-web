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
public class GestorPreguntas {
     private static GestorPreguntas instancia = null;

    private DataSource bd = null;
    private Dao<Pregunta, Integer> preguntaDAO;

    @XmlElementWrapper(name = "preguntas")
    @XmlElement(name = "pregunta")
    private List<Pregunta> preguntas = new ArrayList<>();

    private GestorPreguntas() {
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

    public static GestorPreguntas obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorPreguntas();
        }
        return instancia;
    }

    public int agregar(Pregunta nuevaPregunta) throws SQLException {
        return preguntaDAO.create(nuevaPregunta);
    }

    public Pregunta recuperar(int id) throws SQLException {
        return preguntaDAO.queryForId(id);
    }

    public int actualizar(Pregunta pregunta) throws SQLException {
        return preguntaDAO.update(pregunta);
    }

    public int eliminar(int id) throws SQLException {
        return preguntaDAO.deleteById(id);
    }

    public List<Pregunta> listarTodos() throws SQLException {
        return preguntaDAO.queryForAll();
    }

    public void actualizar() {
        preguntas.clear();
        try {
            preguntas.addAll(listarTodos());
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        actualizar();
        for (Pregunta p : preguntas) {
            result.append(String.format("\n\t%s,", p));
        }
        result.append("\n}");
        return result.toString();
    }
}
