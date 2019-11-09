package ud3ejer208.bdapps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class UD3Ejer208BDAPPS {

    public static void main(String[] args) {

        Statement statement = null;
        Connection con = null;
        Scanner entrada = new Scanner(System.in);
        ArrayList<Integer> listaCodigo = new ArrayList<>();
        int cantidadDatos = 0;

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
            String sentenciaSQL = "SELECT codigo FROM programas";
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            
            while (rs.next())
                listaCodigo.add(rs.getInt("codigo"));
            
            int codigo;
            do {
                System.out.print("Introoduce el codigo para el programa: ");
                codigo = entrada.nextInt();
                if(listaCodigo.contains(codigo) || codigo < 0)
                    System.out.println("El código ya existe o no es válido");
            }while (listaCodigo.contains(codigo) || codigo < 0);
            
            System.out.print("Introduce un nombre del programa: ");
            String nombrePrograma = entrada.nextLine();
            nombrePrograma = entrada.nextLine();

            System.out.print("Introduce Carpeta del programa: ");
            String CarpetaPrograma = entrada.nextLine();


            System.out.print("Introduce la ocupacion de " + nombrePrograma + " : ");
            int ocupacion = entrada.nextInt();


            //Insertando datos en ejecuciones:
            // Crear Statement del Insert
            String sentenciaSQLInsert = "INSERT INTO programas VALUES(" + codigo + ",'" + nombrePrograma + "','"+CarpetaPrograma+"'," + ocupacion + ")";
            cantidadDatos = statement.executeUpdate(sentenciaSQLInsert);
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
        System.out.println("Se ha insertado "+cantidadDatos+" datos con éxito");
    }
}
