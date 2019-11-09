package ud3ejer207.bdapps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UD3Ejer207BDAPPS {

    public static void main(String[] args) {

        Connection con = null;
        Statement statement = null;
        ArrayList<Programa> listaPrograms = new ArrayList<>();

        try {
// Conexión a la BD
            String url;
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/bdapps";
            url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
            String usuario = "root";
            String password = "1234";
            con = DriverManager.getConnection(url, usuario, password);
// Crear Statement de la Consulta
            String sentenciaSQL = "SELECT codigo, nombre, carpeta FROM programas";
            statement = con.createStatement();
// Resulset
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            while (rs.next()) {
                int cod = rs.getInt("codigo");
                String carpeta = rs.getString("carpeta");
                if(carpeta.contains("C:")) {
                    Programa p = new Programa(cod,rs.getString("nombre"),carpeta);
                    listaPrograms.add(p);
                }
                
            }
            rs.close();
// Cerrar conexión
            con.close();
            //formato json en cada objeto:
            for (int i = 0; i < listaPrograms.size(); i++) {
                System.out.println(listaPrograms.get(i));
            }
            
            
        } catch (ClassNotFoundException ce) {
// ce.printStackTrace();
            System.out.println("MySQL no accesible");
            System.out.println(ce.getMessage());
        } catch (SQLException se) {
// se.printStackTrace();
            System.out.println("No se ha podido realizar la sentencia SQL");
            System.out.println(se.getErrorCode() + " " + se.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
}


