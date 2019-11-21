package Clases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import packUtils.Menu;

public class TiendasJson {

    public static void JsonProvincia(String prov) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException, IOException, IOException {
        // Conexión a la BD
        String url;
        Class.forName("com.mysql.jdbc.Driver");
        url = "jdbc:mysql://localhost:3306/bdtelefood";
        url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
        String usuario = "root";
        String password = "1234";
        Connection con = DriverManager.getConnection(url, usuario, password);
// Crear Statement de la Consulta
        String sentenciaSQL = "SELECT * from tiendas where provincia = '" + prov + "'";
        Statement statement = con.createStatement();
// Resulset
        ResultSet rs = statement.executeQuery(sentenciaSQL);

        ArrayList<DatosTienda> datos = new ArrayList<>();

        while (rs.next()) {
            datos.add(new DatosTienda(rs.getString("idtienda"), rs.getString("provincia"), rs.getString("creada"), rs.getString("creada") + ", encargado=" + rs.getString("encargado"), rs.getInt("trabajadores")));
        }
        rs.close();
// Cerrar conexión
        con.close();
        System.out.println(datos.toString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userListaString = gson.toJson(datos);
        System.out.println(datos);

        
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("/Datos/"+prov+".json"));
        bw.write(userListaString);
        bw.close();

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Menu.Mostrar();
    }
}
