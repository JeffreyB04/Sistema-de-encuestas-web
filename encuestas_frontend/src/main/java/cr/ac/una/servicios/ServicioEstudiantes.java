package cr.ac.una.servicios;

import cr.ac.una.estudiantes.entidades.Estudiante;
import cr.ac.una.estudiantes.entidades.GestorEstudiantes;
import cr.ac.una.html.HtmlElement;
import cr.ac.una.html.HtmlTable;
import cr.ac.una.html.HtmlTableRow;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServicioEstudiantes implements Serializable {

    public ServicioEstudiantes() {
        this.estudiantes = GestorEstudiantes.obtenerInstancia();
    }

    public void agregar(Estudiante nuevo) throws IllegalArgumentException, SQLException {
        try {
            int r = estudiantes.agregar(nuevo);
            if (r != 1) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw ex;
        }
    }

    public Estudiante recuperar(String id) throws SQLException {
        return estudiantes.recuperar(id);
    }

    public void borrar(String id) throws IllegalArgumentException, SQLException {
        try {
            int r = estudiantes.eliminar(id);
            if (r != 1) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw ex;
        }
    }

    public HtmlTable tablaEstudiantesHTML() {
        HtmlTable tabla = new HtmlTable();
        tabla.setAttribute("class", "tablaEstudiantes");

        HtmlElement h = tabla.getTableHeader();
        HtmlTableRow encabezado = new HtmlTableRow();
        encabezado.appendCell("Id").setAttribute("class", "c1");
        encabezado.appendCell("Apellidos").setAttribute("class", "c2");
        encabezado.appendCell("&nbsp;").setAttribute("class", "c2");
        encabezado.appendCell("Nombre").setAttribute("class", "c2");
        encabezado.appendCell("Nacimiento").setAttribute("class", "c2");
        encabezado.appendCell("&nbsp;").setAttribute("style", "width: 24px;");
        h.appendChild(encabezado);

        HtmlElement b = tabla.getTableBody();
        try {
            for (Estudiante e : estudiantes.listarTodos()) {
                HtmlTableRow fila = new HtmlTableRow();
                fila.appendCell(e.getId()).setAttribute("class", "c1");
                fila.appendCell(e.getApellido1()).setAttribute("class", "c2");
                fila.appendCell(e.getApellido2()).setAttribute("class", "c2");
                fila.appendCell(e.getNombre()).setAttribute("class", "c2");
                fila.appendCell(e.getNacimiento().toString()).setAttribute("class", "c2");
                fila.appendCell(
                        String.format("<button onclick='borrarRegistro(\"%s\");'><i class=\"fa fa-trash-o\"></i></button>",
                                e.getId()))
                        .setAttribute("style", "font-size: larger; text-align: center; width: 24px;");
                b.appendChild(fila);
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return tabla;
    }

    public static String tablaEstudiantes(ServicioEstudiantes instancia) {
        return instancia.tablaEstudiantesHTML().toString(4);
    }

    public String verificarId(String txt) {
        String r = txt;

        Pattern pat = Pattern.compile("([1-9,A])-?([0-9]{4})-?([0-9]{4})");
        Matcher m = pat.matcher(txt);
        if (m.find()) {
            r = String.format("%s%s%s", m.group(1), m.group(2), m.group(3));
        }

        return r;
    }

    private GestorEstudiantes estudiantes = null;
}
