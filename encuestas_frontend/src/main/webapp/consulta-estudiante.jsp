
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Registro de estudiantes</title>
        <link rel="shortcut icon" href="css/imagenes/GA_icon.png">
        <link rel="stylesheet" href="css/default.css" type="text/css"/>
        <jsp:useBean id="e" class="cr.ac.una.servicios.ServicioEstudiantes"
                     scope="application"></jsp:useBean>
    </head>
    <body>
        <div id="wrapper">
            <header>
                <h1>Registro de estudiantes</h1>
                <h2>Consulta</h2>
                <nav>
                    <ul> 
                        <li>
                            <a href="listado.jsp">Lista de estudiantes</a>
                        </li>
                        <li class="active">
                            <a href="consulta-estudiante.jsp">Formulario de consulta</a>
                        </li>
                    </ul>
                </nav>
            </header>
            <div id="contents">
                <form method="GET" action="consulta-estudiante" class="info">
                    <p>
                        <label>Identificación del (la) estudiante:&nbsp;</label>
                        <input type="text" name="id" autofocus="autofocus"
                               size="16" maxlength="12"
                               placeholder="(digite la identificación)"/>
                    </p>
                    <p style="text-align: right;">
                        <button type="submit">Consultar</button>
                    </p>
                </form>
            </div>
            <footer></footer>
        </div>
    </body>
</html>
