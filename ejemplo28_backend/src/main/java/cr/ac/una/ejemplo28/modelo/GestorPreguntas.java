package cr.ac.una.ejemplo28.modelo;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
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

        // Proporcionar la clase del contexto inicial
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");

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
            preguntaDAO = DaoManager.createDao(connectionSource, Pregunta.class);
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
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

    public List<Pregunta> obtenerPreguntasPorEncuesta(int encuestaId) throws SQLException {
        QueryBuilder<Pregunta, Integer> queryBuilder = preguntaDAO.queryBuilder();
        Where<Pregunta, Integer> where = queryBuilder.where();
        where.eq("encuesta_id", encuestaId);

        return queryBuilder.query();
    }

    public String obtenerEnunciadoPregunta(int preguntaId) throws SQLException {
        Pregunta pregunta = recuperar(preguntaId);
        return (pregunta != null) ? pregunta.getEnunciado() : "";
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
