package ud3ejer201.bdtareas;

import java.sql.*;

public class UD3Ejer201BDTAREAS {

    public static void main(String[] args) {
        try {
// Conexión a la BD
            String url;
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/bdtareas";
            url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
            String usuario = "root";
            String password = "1234";
            Connection con = DriverManager.getConnection(url, usuario, password);
// Crear Statement de la Consulta
            String sentenciaSQL = "SELECT * FROM tareas";
            Statement statement = con.createStatement();
// Resulset
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            System.out.printf("%-30s %-30s %-30s %-30s\n", "Código", "Descripción", "Fecha Prevista", "Terminada");
            System.out.print("------------------------------------");
            System.out.print(" ");
            System.out.print("------------------------------------");
            System.out.print(" ");
            System.out.print("------------------------------------");
            System.out.println();
            while (rs.next()) {
                System.out.printf("%-30s %-30s %-30s %-30s\n", rs.getString("codigo"), rs.getString("descripcion"),
                        rs.getString("fechaProvista"), ConvierteSioNO(rs.getString("Terminada")));
            }
            rs.close();
// Cerrar conexión
            con.close();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("Terminado!");
    }
    
    private static String ConvierteSioNO(String numString) {
        if(Integer.parseInt(numString) == 0) {
            return "No";
        }
        else
            return "Sí";
    }
}
