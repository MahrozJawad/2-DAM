package ud3ejer204.bdapps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class UD3Ejer204BDAPPS {

    public static void main(String[] args) {

        Statement statement = null;
        Connection con = null;
        Scanner entrada = new Scanner(System.in);
        ArrayList<String> listaSentenciasSQL = new ArrayList<>();

        try {
// Conexión a la BD
            String url;
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/bdapps";
            url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
            String usuario = "root";
            String password = "1234";
            con = DriverManager.getConnection(url, usuario, password);
            statement = con.createStatement();

            //leyendo base de datos:
            // Crear Statement de la Consulta
            String sentenciaSQL = "SELECT codigo,nombre FROM programas";
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            while (rs.next()) {
                String codprograma = rs.getString("codigo");
                System.out.print("Introduce la ocupacion de " + rs.getString("nombre") + " : ");
                int ocupacion = entrada.nextInt();

                // Crear Statement del Update
                String sentenciaSQLUpdate = "Update programas"
                        + " SET ocupacion=" + ocupacion
                        + " WHERE " + codprograma + "=" + "codigo";
                listaSentenciasSQL.add(sentenciaSQLUpdate);

                //Insertando datos en ejecuciones:
                // Crear Statement del Insert
                String sentenciaSQLInsert = "INSERT INTO ejecuciones VALUES(" + 0 + "," + codprograma + ",CURDATE(),'" + usuario + "')";
                listaSentenciasSQL.add(sentenciaSQLInsert);
            }
            rs.close();

            for (int i = 0; i < listaSentenciasSQL.size(); i++) {
                // Execute Actualizando datos de programa en ocupacion 
                statement.executeUpdate(listaSentenciasSQL.get(i++));
                // Execute Insertando datos ejecuciones
                statement.executeUpdate(listaSentenciasSQL.get(i));
            }
            //insertando una vez más
            statement.executeUpdate(listaSentenciasSQL.get(listaSentenciasSQL.size() - 1));

            //Comprobando los datos insertados.
            System.out.println("Comprobando los datos de la tabla programas");
            sentenciaSQL = "SELECT *  FROM programas";
            statement = con.createStatement();
// Resulset
            rs = statement.executeQuery(sentenciaSQL);
            System.out.printf("%-60s %-60s %-60s %-60s\n", "Codigo", "nombre", "carpeta", "ocupacion");
            System.out.print("------------------------------------------------------------------------------------------------------------------------");
            System.out.print(" ");
            System.out.print("------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            while (rs.next()) {
                System.out.printf("%-60s %-60s %-60s %-60s\n", rs.getString("Codigo"), rs.getString("nombre"), rs.getString("carpeta"), rs.getString("ocupacion"));
            }
            rs.close();
            
            System.out.println("Comprobando los datos de la tabla ejecuciones");
            sentenciaSQL = "SELECT * FROM ejecuciones";
            statement = con.createStatement();
// Resulset
            rs = statement.executeQuery(sentenciaSQL);
            System.out.printf("%-60s %-60s %-60s %-60s\n", "id", "codprograma", "fecha", "usuario");
            System.out.print("------------------------------------------------------------------------------------------------------------------------");
            System.out.print(" ");
            System.out.print("------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            while (rs.next()) {
                System.out.printf("%-60s %-60s %-60s %-60s\n", rs.getString("id"), rs.getString("codprograma"), rs.getString("fecha"), rs.getString("usuario"));
            }
            rs.close();

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
        System.out.println("Datos insertados con éxito.");
    }

}
