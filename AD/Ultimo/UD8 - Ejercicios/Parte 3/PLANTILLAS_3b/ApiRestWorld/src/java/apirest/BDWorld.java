
package apirest;

import clases.City;
import clases.Country;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BDWorld {
    private static Connection conexion;
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        String url = "jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull";
        String usuario = "root";
        String password = "1234";
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static City getCity(int id) {

        City city = null;

        try {
            String sentenciaSQL = "SELECT * FROM city WHERE id = " + id;
            Statement statement = conexion.createStatement();

            ResultSet rs = statement.executeQuery(sentenciaSQL);
            if (rs.next()) {
                city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setDistrict(rs.getString("district"));
                city.setPopulation(String.valueOf(rs.getInt("population")));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return city;
    }
    
    public static Country getCountry(String code) {
        Country country = null;
        try {
            String sentenciaSQL = "SELECT * FROM City WHERE countrycode='" + code + "'" ;
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            
            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                cities.add(getCity(rs.getInt("id")));
            }
            rs.close();
            
            sentenciaSQL = "SELECT * FROM Pais WHERE code='" + code + "'";
            statement = conexion.createStatement();
            rs = statement.executeQuery(sentenciaSQL);
            
            if (rs.next()) {
                country = new Country();
                country.setCode(rs.getString("code"));
                country.setName(rs.getString("name"));
                country.setCapital(getCity(rs.getInt("capital")));
                country.setContinent(rs.getString("continent"));
                country.setSurfacearea(rs.getDouble("surfacearea"));
            }
            rs.close();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return country;
    }

}
