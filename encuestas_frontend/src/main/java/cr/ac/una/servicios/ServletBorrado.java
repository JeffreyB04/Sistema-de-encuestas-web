package cr.ac.una.servicios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletBorrado", urlPatterns = {"/ServletBorrado"})
public class ServletBorrado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        try {
            if (id != null) {

                ServicioEstudiantes estudiantes
                        = (ServicioEstudiantes) getServletContext().getAttribute("e");

                estudiantes.borrar(id);
                System.out.printf("ServletBorrado: Se eliminó el estudiante '%s'.%n", id);

            } else {
                System.err.println("ServletBorrado: No se indicó el id del estudiante.");
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf(
                    "ServletBorrado: No se encontró el estudiante '%s'.%n", id);
        }

        response.sendRedirect("listado.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
