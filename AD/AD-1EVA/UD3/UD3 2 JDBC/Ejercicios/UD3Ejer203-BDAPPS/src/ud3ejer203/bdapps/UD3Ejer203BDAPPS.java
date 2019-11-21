package ud3ejer203.bdapps;

import java.sql.*;

public class UD3Ejer203BDAPPS {

    public static void main(String[] args) {
        try {
// Conexión a la BD
            String url;
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/bdapps";
            url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
            String usuario = "root";
            String password = "1234";
            Connection con = DriverManager.getConnection(url, usuario, password);
// Crear Statement de la Consulta
            String sentenciaSQL = "ALTER TABLE programas ADD COLUMN ocupacion INT DEFAULT 0";
            Statement statement = con.createStatement();
            
            statement.executeUpdate(sentenciaSQL);
// Cerrar conexión
            con.close();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("Terminado! se ha Añadido una columla 'Ocupacion' en programas");
    }
}

