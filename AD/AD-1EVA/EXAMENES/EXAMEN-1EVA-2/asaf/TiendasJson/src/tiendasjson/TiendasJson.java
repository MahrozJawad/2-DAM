package tiendasjson;

import clases.Emails;
import clases.Tienda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.Connection;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import packUtils.Menu;

public class TiendasJson {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        
        Menu.Mostrar();
        
    }
        
    public static void jsonTiendas(String prov) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        // Conexión a la BD
        String url;

        Class.forName("com.mysql.jdbc.Driver");
        url = "jdbc:mysql://localhost:3306/bdtelefood";
        url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
        String usuario = "root";
        String password = "1234";
        Connection con = (Connection) DriverManager.getConnection(url, usuario, password);

        // Crear Statement de la Consulta
        String sentenciaSQL = "SELECT idtienda, provincia, creada, encargado, trabajadores  FROM tiendas WHERE provincia='"+prov+"'";
        Statement statement = con.createStatement();

        // Resulset
        ResultSet rs = statement.executeQuery(sentenciaSQL);
        ArrayList<Tienda> listaTiendas = new ArrayList<>();
        while (rs.next()) {
            Tienda tienda = new Tienda();
            tienda.setIdtienda(rs.getString("idtienda"));
            tienda.setProvincia(rs.getString("provincia"));
            tienda.setCreada(rs.getString("creada"));
            tienda.setEncargado(rs.getString("encargado"));
            tienda.setTrabajadores(rs.getInt("trabajadores"));
            
            System.out.println(tienda.toString());
			
            // ***************************************
            // Buscar y cargar los emails de la tienda
            String sentenciaSQL2 = "SELECT idtienda, email FROM emails WHERE idtienda='"+rs.getString("idtienda")+"'";
            Statement statement2 = con.createStatement();

            // Resulset
            ResultSet rs2 = statement2.executeQuery(sentenciaSQL2);
            ArrayList<Emails> listaEmails = new ArrayList<>();
            while (rs2.next()) {
                Emails email = new Emails();
                email.setIdtienda(rs2.getString("idtienda"));
                email.setEmail(rs2.getString("email"));
                listaEmails.add(email);
            }
            rs2.close();
            // ***************************************
			
            tienda.setEmails(listaEmails); // Añadir la lista de emails

            listaTiendas.add(tienda);	// Añadir tienda a la lista
            
        }
        rs.close();

        // Cerrar conexión
        con.close();

        // *****************************
        // Crear fichero JSON con Gson
        // *****************************
            
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String strJsonTiendas = gson.toJson(listaTiendas);
        // System.out.println(strJsonTiendas);
            
        String nomFile = "./datos/"+prov+".json";
        BufferedWriter ficheroSalida = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomFile), StandardCharsets.UTF_8));
        ficheroSalida.write(strJsonTiendas);
        ficheroSalida.close();            
                                
        
    }

}
