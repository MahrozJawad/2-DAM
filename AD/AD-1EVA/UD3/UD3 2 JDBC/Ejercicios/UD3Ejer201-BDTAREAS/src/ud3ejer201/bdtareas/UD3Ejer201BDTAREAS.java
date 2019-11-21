package ud3ejer201.bdtareas;

import java.sql.*;

public class UD3Ejer201BDTAREAS {

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
            String sentenciaSQL = "CREATE TABLE ejecuciones("
                            + "id INT AUTO_INCREMENT PRIMARY KEY,"
                            + "codprograma VARCHAR(50),"
                            + "fecha DATE,"
                            + "usuario VARCHAR(50)"
                            + ")";
            Statement statement = con.createStatement();
// Resulset
            statement.executeUpdate(sentenciaSQL);
// Cerrar conexión
            statement.close();
            con.close();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("Terminado! se ha creado la tabla ejecuciones con éxito");
    }
}

