package consultabd3;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConsultaBD3 {

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {
// Conexión a la BD
        String url;
        Class.forName("com.mysql.jdbc.Driver");
        url = "jdbc:mysql://localhost:3306/biblioteca";
        url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
        String usuario = "root";
        String password = "1234";
        Connection con = (Connection) DriverManager.getConnection(url, usuario, password);
// Crear Statement del Insert
        String sentenciaSQL = "INSERT INTO libros (id, titulo, autor) VALUES "
                + "(4,'20.000 Leguas de Viaje Submarino', 'Julio Verne'), "
                + "(5,'Alicia en el País de las Maravillas','Lewis Carrol');";
        Statement statement = (Statement) con.createStatement();
// Execute
        int cantidad = statement.executeUpdate(sentenciaSQL);
        System.out.println("Datos insertados: " + cantidad);
// Cerrar conexión
        con.close();
    }
}
