package consultabd2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaBD2 {

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
// Crear Statement del CREATE TABLE
        String sentenciaSQL = "CREATE TABLE personas ( "
                + "codigo VARCHAR(4) PRIMARY KEY, "
                + "nombre VARCHAR(50), "
                + "email VARCHAR(40) " + ");";
        Statement statement = con.createStatement();
// Execute
        statement.executeUpdate(sentenciaSQL);
        System.out.println("Tabla personas creada");
// Cerrar conexión
        con.close();
    }
}
