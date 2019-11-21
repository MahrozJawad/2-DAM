
package ud3ejer206.bdapps;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UD3Ejer206BDAPPS {

    public static void main(String[] args) {

        Statement statement = null;
        java.sql.Connection con = null;
        Scanner entrada = new Scanner(System.in);

        try {
// Conexión a la BD
            // Conexión a la BD
            String url;
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/bdapps";
            url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
            String usuario = "root";
            String password = "1234";
            con = DriverManager.getConnection(url, usuario, password);
// Execute
            statement = con.createStatement();
            
            System.out.print("Introduce una fecha para saber la cantidad de ejecuciones de esa fecha: ");
            
            
            String consulta = "SELECT numejecuciones('"+entrada.nextLine()+"')";
            ResultSet rs = statement.executeQuery(consulta);
            
            rs.next();
            String valor = ""+rs.getString(1);
            System.out.println("El número de ejecuciones ese dia es: " + valor);
            
// Cerrar conexión
            con.close();

        } catch (ClassNotFoundException ce) {
// ce.printStackTrace();
            System.out.println("MySQL no accesible");
            System.out.println(ce.getMessage());
        } catch (SQLException se) {
// se.printStackTrace();
            System.out.println("No se ha podido realizar la Sentencia SQL");
            System.out.println(se.getErrorCode() + " " + se.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                };
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                };
            }
        }
        System.out.println("Terminado!");
    }

}
