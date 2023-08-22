/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;



import OEEC20230821ACCESO.LibreriaDAL;
import Entidad.Libreria;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

@WebServlet(name = "Libreriaservlet", urlPatterns = {"/Libreriaservlet"})
public class Libreriaservlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        
        if (action != null) {
            if (action.equals("registrar")) {
                registrarLibro(request, response);
            } else if (action.equals("borrar")) {
                borrarLibro(request, response);
            }
        } else {
            listarLibros(request, response);
        }
    }

    private void registrarLibro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int anio = Integer.parseInt(request.getParameter("anio"));

        Libreria libro = new Libreria(titulo, autor, anio);

        try {
            int result = LibreriaDAL.registrarLibro(libro);
            if (result > 0) {
                response.sendRedirect("Libreriaservlet");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void borrarLibro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            int result = LibreriaDAL.borrarLibro(id);
            if (result > 0) {
                response.sendRedirect("Libreriaservlet");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void listarLibros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Libreria> libros = LibreriaDAL.obtenerLibrosRegistrados();
            request.setAttribute("librosRegistrados", libros);
            request.getRequestDispatcher("libreria.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
