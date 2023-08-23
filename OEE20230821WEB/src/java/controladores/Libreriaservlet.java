/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;





import Entidad.Libreria;
import OEEC20230821ACCESO.LibreriaDAL;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import utils.Utilidad;

@WebServlet(name = "Libreriaservlet", urlPatterns = {"/Libreria"})
public class Libreriaservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = Utilidad.getParameter(request, "action", "");

        switch (action) {
            case "registrar" -> registrarLibro(request, response);
            case "borrar" -> borrarLibro(request, response);
            default -> listarLibros(request, response);
        }
    }

    private void registrarLibro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = Utilidad.getParameter(request, "titulo", "");
        String autor = Utilidad.getParameter(request, "autor", "");
        int anio = Integer.parseInt(Utilidad.getParameter(request, "anio", "0"));

        Libreria libro = new Libreria(titulo, autor, anio);

        try {
            int result = LibreriaDAL.registrarLibro(libro);
            if (result > 0) {
                response.sendRedirect("Libreriaservlet");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            response.sendRedirect("error.jsp");
        }
    }

    private void borrarLibro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(Utilidad.getParameter(request, "id", "0"));

        try {
            int result = LibreriaDAL.borrarLibro(id);
            if (result > 0) {
                response.sendRedirect("Libreriaservlet");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            response.sendRedirect("error.jsp");
        }
    }

    private void listarLibros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Libreria> libros = LibreriaDAL.obtenerLibrosRegistrados();
            request.setAttribute("librosRegistrados", libros);
            request.getRequestDispatcher("Libreria.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
