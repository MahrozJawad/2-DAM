package llamadaafuncionsencilla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class LlamadaAFuncionSencilla {

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
// Execute
        Statement statement = con.createStatement();
        String consulta = "SELECT totallibros()";
        ResultSet rs = statement.executeQuery(consulta);
        rs.next();
        System.out.println("El total de libros es: " + rs.getInt(1));
// Cerrar conexión
        con.close();
    }
}
