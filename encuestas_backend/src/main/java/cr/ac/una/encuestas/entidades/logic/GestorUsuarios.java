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
public class GestorUsuarios {
     private static GestorUsuarios instancia = null;

    private DataSource bd = null;
    private Dao<Usuario, String> usuarioDAO;

    @XmlElementWrapper(name = "usuarios")
    @XmlElement(name = "usuario")
    private List<Usuario> usuarios = new ArrayList<>();

    private GestorUsuarios() {
        // Implementa la inicialización del gestor de usuarios de manera similar al gestor de encuestas
    }

    public static GestorUsuarios obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }

    public int agregar(Usuario nuevoUsuario) throws SQLException {
        return usuarioDAO.create(nuevoUsuario);
    }

    public Usuario recuperar(String id) throws SQLException {
        return usuarioDAO.queryForId(id);
    }

    public int actualizar(Usuario usuario) throws SQLException {
        return usuarioDAO.update(usuario);
    }

    public int eliminar(String id) throws SQLException {
        return usuarioDAO.deleteById(id);
    }

    public List<Usuario> listarTodos() throws SQLException {
        return usuarioDAO.queryForAll();
    }

    public void actualizar() {
        usuarios.clear();
        try {
            usuarios.addAll(listarTodos());
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        actualizar();
        for (Usuario u : usuarios) {
            result.append(String.format("\n\t%s,", u));
        }
        result.append("\n}");
        return result.toString();
    }
}
