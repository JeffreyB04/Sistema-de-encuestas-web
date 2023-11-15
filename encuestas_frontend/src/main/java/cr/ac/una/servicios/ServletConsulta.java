package cr.ac.una.servicios;

import cr.ac.una.estudiantes.entidades.Estudiante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "ServicioEstudiante",
        urlPatterns = {"/ServicioEstudiante", "/consulta-estudiante"}
)
public class ServletConsulta extends HttpServlet {

    private void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        try {
            if (id != null) {
                ServicioEstudiantes estudiantes
                        = (ServicioEstudiantes) getServletContext().getAttribute("e");

                try {
                    Estudiante e = estudiantes.recuperar(id);
                    if (e != null) {
                        request.setAttribute("registroEstudiante", e);
                    }
                } catch (SQLException ex) {
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/estudiantes/vista/registro-estudiante.jsp");
            dispatcher.forward(request, response);

        } catch (IOException | ServletException ex) {
            System.err.printf("ServletConsulta: '%s'%n", ex.getMessage());
        }

    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
