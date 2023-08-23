<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidad.Libreria"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controladores.Libreriaservlet"%>
<!DOCTYPE html>
<html>
  <head>        
        <jsp:include page="/Views/Shared/title.jsp" />    
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
    <%-- Obtener la lista de libros registrados del atributo "librosRegistrados" --%>
    <% List<Libreria> librosRegistrados = (List<Libreria>) request.getAttribute("librosRegistrados"); %>
    <main class="container">
        <div class="row">
            <div class="col l12 s12">
                <h5>Crear Libro</h5>
                <!-- Formulario para registrar un nuevo libro -->
                <form action="LibreriaServlet" method="post">
                    <input type="hidden" name="action" value="registrar">
                    <div class="input-field col l3 s12">
                        <input id="txtTitulo" type="text" name="titulo" required class="validate" maxlength="50">
                        <label for="txtTitulo">Título</label>
                    </div>
                    <div class="input-field col l8 s12">
                        <input id="txtAutor" type="text" name="autor" required class="validate" maxlength="50">
                        <label for="txtAutor">Autor</label>
                    </div>
                    <div class="input-field col l1 s12">
                        <input id="numAnio" type="number" name="anio" required class="validate">
                        <label for="numAnio">Año</label>
                    </div>
                    <div class="input-field col l12 s12">
                        <button type="submit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col l12 s12">
                <div style="overflow: auto">
                    <table class="paginationjs">
                        <thead>
                            <tr>
                                <td style="text-align: center" colspan="4">Libros Registrados</td>
                            </tr>
                            <tr>
                                <th>Título</th>
                                <th>Autor</th>
                                <th>Año de Publicación</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Libreria libro : librosRegistrados) { %>
                            <tr>
                                <td><%= libro.getTitulo() %></td>
                                <td><%= libro.getAutor() %></td>
                                <td><%= libro.getAnioPublicacion() %></td>
                                <td>
                                    <!-- Acciones para cada libro -->
                                    <form action="LibreriaServlet" method="post">
                                        <input type="hidden" name="action" value="borrar">
                                        <input type="hidden" name="id" value="<%= libro.getId() %>">
                                        <button type="submit" class="waves-effect waves-light btn red"><i class="material-icons right">delete</i>Borrar</button>
                                    </form>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
                  <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPageComplete%>" />                        
                    </jsp:include>
                </div>
                      </div>
    </main>
     <jsp:include page="/Views/Shared/footerBody.jsp" />
    <footer>
     
    </footer>
 
</body>
</html>
