package cr.ac.una.encuestas.entidades;
/*
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

@XmlRootElement(name = "lista_estudiantes")
public class GestorEstudiantes implements Serializable {

    private GestorEstudiantes() {
        try {
            InitialContext ctx = new InitialContext();
            bd = (DataSource) ctx.lookup("jdbc/bd_estudiantes");
            System.out.println("Usando JNDI para acceder a la base de datos..");

        } catch (NamingException | NullPointerException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());

            bd = new MysqlDataSource();
            MysqlDataSource mds = (MysqlDataSource) bd;
            mds.setServerName("localhost");
            mds.setPortNumber(3306);
            mds.setDatabaseName("bd_estudiantes");
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
            estudianteDAO = DaoManager.createDao(connectionSource, Estudiante.class);
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    public static GestorEstudiantes obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorEstudiantes();
        }
        return instancia;
    }

    public int agregar(Estudiante nuevo) throws SQLException {
        return estudianteDAO.create(nuevo);
    }

    public Estudiante recuperar(String id) throws SQLException {
        return estudianteDAO.queryForId(id);
    }

    public int actualizar(Estudiante estudiante) throws SQLException {
        return estudianteDAO.update(estudiante);
    }

    public int eliminar(String id) throws SQLException {
        return estudianteDAO.deleteById(id);
    }

    public List<Estudiante> listarTodos() throws SQLException {
        return estudianteDAO.queryForAll();
    }

    public void actualizar() {
        estudiantes.clear();
        try {
            estudiantes.addAll(listarTodos());
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder("{");
        actualizar();
        for (Estudiante e : estudiantes) {
            r.append(String.format("\n\t%s,", e));
        }
        r.append("\n}");
        return r.toString();
    }

    private static GestorEstudiantes instancia = null;

    private DataSource bd = null;
    private Dao<Estudiante, String> estudianteDAO;

    @XmlElementWrapper(name = "estudiantes")
    @XmlElement(name = "estudiante")
    private List<Estudiante> estudiantes = new ArrayList<>();
}
*/