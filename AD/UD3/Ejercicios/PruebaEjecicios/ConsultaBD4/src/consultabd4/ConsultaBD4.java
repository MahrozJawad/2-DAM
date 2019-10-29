package consultabd4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConsultaBD4 {

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {
// Conexión a la BD
        String url;
        Class.forName("com.mysql.jdbc.Driver");
        url = "jdbc:mysql://localhost:3306/biblioteca";
        url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
        String usuario = "root";
        String password = "1234";
        Connection con = DriverManager.getConnection(url, usuario, password);
// Crear Statement de la Consulta
        String sentenciaSQL = "SELECT titulo, autor FROM libros WHERE autor = ?";
        PreparedStatement pStatement = con.prepareStatement(sentenciaSQL);
        pStatement.setString(1, "Julio Verne");
// Resulset
        ResultSet rs = pStatement.executeQuery();
        System.out.printf("%-60s %-60s\n", "Título", "Autor");
        System.out.print("------------------------------------------------------------");
        System.out.print(" ");
        System.out.print("------------------------------------------------------------");
        System.out.println();
        while (rs.next()) {
            System.out.printf("%-60s %-60s\n", rs.getString("titulo"), rs.getString("autor"));
        }
        rs.close();
// Cerrar conexión
        con.close();
    }
}
