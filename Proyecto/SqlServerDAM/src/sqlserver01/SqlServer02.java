
package sqlserver01;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class SqlServer02 {

    public static void main(String[] args) {

        // Connect to database
        String hostName = "vricodbserver.database.windows.net"; // update me
        String dbName = "damdatabase"; // update me
        String user = "adminserver"; // update me
        String password = "vrc#1475"; // update me
        String connectionUrl = "jdbc:sqlserver://XXXXXXXXXXX.database.windows.net:1433;";
	connectionUrl += "databaseName=damdatabase;user=XXXXXXXX;password=XXXXXX";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            String schema = connection.getSchema();
            System.out.println("Successful connection - Schema: " + schema);

            System.out.println("Query data example:");
            System.out.println("=========================================");

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT TOP 20 pc.Name as CategoryName, p.name as ProductName "
                + "FROM [SalesLT].[ProductCategory] pc "  
                + "JOIN [SalesLT].[Product] p ON pc.productcategoryid = p.productcategoryid";

            try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql)) {

                // Print results from select statement
                System.out.println("Top 20 categories:");
                while (resultSet.next())
                {
                    System.out.println(resultSet.getString(1) + " "
                        + resultSet.getString(2));
                }
                connection.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}



