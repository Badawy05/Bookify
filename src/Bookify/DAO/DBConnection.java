package Bookify.DAO;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection connectDB()
    {
    String url =
        "jdbc:mysql://localhost:3306/Bookify?useSSL=false&allowPublicKeyRetrieval=true";

        String user = "root";
        String pass = "wasd1234";

        try 
        {
             return DriverManager.getConnection(url, user, pass);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }
}

