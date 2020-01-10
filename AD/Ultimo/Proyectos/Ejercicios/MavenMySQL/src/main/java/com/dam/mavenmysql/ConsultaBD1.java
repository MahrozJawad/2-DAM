
package com.dam.mavenmysql;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 

public class ConsultaBD1 { 
  public static void main(String[] args) 
                     throws ClassNotFoundException, SQLException { 

    // Conexión a la BD
    String url;

    Class.forName("com.mysql.jdbc.Driver"); 
    url = "jdbc:mysql://localhost:3306/biblioteca";
    url +="?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull"; 
    String usuario = "root"; 
    String password = "1234"; 
    Connection con = DriverManager.getConnection(url, usuario, password);
    
    // Crear Statement de la Consulta
    String sentenciaSQL = "SELECT titulo, autor FROM libros"; 
    Statement statement = con.createStatement(); 

    // Resulset
    ResultSet rs = statement.executeQuery(sentenciaSQL);
    System.out.printf("%-60s %-60s\n", "Título","Autor");
    System.out.print("------------------------------------------------------------"); 
    System.out.print(" ");
    System.out.print("------------------------------------------------------------"); 
    System.out.print("\n");
    while (rs.next()) { 
        System.out.printf("%-60s %-60s\n", rs.getString("titulo"), rs.getString("autor"));
    } 
    rs.close(); 

    // Cerrar conexión
    con.close(); 
  } 
  
  
}
