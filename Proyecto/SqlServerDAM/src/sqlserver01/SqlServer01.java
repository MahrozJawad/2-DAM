
package sqlserver01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

        
public class SqlServer01 {
    public static void main(String[] args) throws ClassNotFoundException {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://XXXXXXXXXXX.database.windows.net:1433;";
	connectionUrl += "databaseName=damdatabase;user=XXXXXXXX;password=XXXXXX";
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
        
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM SalesLT.Product";
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println(rs.getString("ProductID") + " " + rs.getString("Name"));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
