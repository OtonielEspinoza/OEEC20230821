/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OEEC20230821ACCESO;

import Entidad.Libreria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibreriaDAL {

    public static int registrarLibro(Libreria libro) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO Libros (Titulo, Autor, AnioPublicacion) VALUES (?, ?, ?)";
        try (Connection connection = ComunDB.obtenerConexion(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setInt(3, libro.getAnioPublicacion());
            result = statement.executeUpdate();
        }
        return result;
    }

    public static List<Libreria> obtenerLibrosRegistrados() throws SQLException {
        List<Libreria> librosRegistrados = new ArrayList<>();
        String sql = "SELECT * FROM Libros";
        try (Connection connection = ComunDB.obtenerConexion();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Libreria libro = resultSetToLibro(resultSet);
                librosRegistrados.add(libro);
            }
        }
        return librosRegistrados;
    }
    
     public static int borrarLibro(int id) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM Libros WHERE ID = ?";
        try (Connection connection = ComunDB.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        }
        return result;
     }

   private static Libreria resultSetToLibro(ResultSet resultSet) throws SQLException {
    int id = resultSet.getInt("ID");
    String titulo = resultSet.getString("Titulo");
    String autor = resultSet.getString("Autor");
    int anioPublicacion = resultSet.getInt("AnioPublicacion");
    return new Libreria(id, titulo, autor, anioPublicacion);
}

}


