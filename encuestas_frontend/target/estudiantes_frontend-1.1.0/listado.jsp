
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/estudiantes.tld" prefix="estudiantes" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Registro de estudiantes</title>
        <link rel="shortcut icon" href="css/imagenes/GA_icon.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/default.css" rel="stylesheet" type="text/css"/>
        <link href="css/tablas.css" rel="stylesheet" type="text/css"/>
        <script src="js/scripts.js" type="text/javascript"></script>
        <jsp:useBean id="e" class="cr.ac.una.servicios.ServicioEstudiantes"
                     scope="application"></jsp:useBean>
        </head>
        <body>
            <div id="wrapper">
                <header>
                    <h1>Registro de estudiantes</h1>
                    <h2>Lista general de estudiantes</h2>
                    <nav>
                        <ul> 
                            <li class="active">
                                <a href="listado.jsp">Lista de estudiantes</a>
                            </li>
                            <li>
                                <a href="consulta-estudiante.jsp">Formulario de consulta</a>
                            </li>
                        </ul>
                    </nav>
                </header>
                <div id="contents">
                    <div class="info" style="border: none; ">
                    <%--
                      La tabla puede mostrarse usando una expresión de Java
                      de varias maneras.
                      Se puede crear la instancia de la clase GestorEstudiantes
                      para invocar al método que produce la tabla.

                      También se puede usar un bean para usarlo ya sea
                      directamente o como parámetro para la versión estática
                      del método, o usar una instancia creada explícitamente
                      para invocar al método.

                      Por último, podemos definir una biblioteca de etiquetas
                      (Tag Library) para invocar al método a través del bean.
                    --%>
                    ${estudiantes:tablaEstudiantes(e)}
                </div>
            </div>
            <footer></footer>
        </div>
    </body>
</html>
