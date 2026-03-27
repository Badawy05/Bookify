package Bookify.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bookify.Model.PasswordUtil;

public class CustomerDAO 
{
    private static boolean checkPass(String username,String pass)
    {
          String query = "SELECT 1 FROM Customer WHERE Pass = ? and Username= ?";

        try (
            Connection conn = DBConnection.connectDB();
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setString(1, pass);
            ps.setString(2, username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    private static boolean userExists(String username)
    {
         String query = "SELECT 1 FROM Customer WHERE Username= ?";

        try (
            Connection conn = DBConnection.connectDB();
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public static boolean loginCheck(String username,String pass)
    {
        pass=PasswordUtil.hashPassword(pass);
                if(checkPass(username, pass))
                {
                    return true;
                }
                return false;
    }
    public static boolean AddNewUser(String username,String pass,String Full_Name,String email,String phone,String city,String country)
    {
        if (!userExists(username))
        {
        String query = """
        INSERT INTO Customer 
        (Username,Pass,Full_Name,Email,Phone,city,country) 
        values(?,?,?,?,?,?,?)""";
        pass=PasswordUtil.hashPassword(pass);
        try (
            Connection conn = DBConnection.connectDB();
            PreparedStatement ps = conn.prepareStatement(query)
        ) 
        {
        ps.setString(1, username);
        ps.setString(2, pass);
        ps.setString(3, Full_Name);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setString(6, city);
        ps.setString(7, country);
        int rows = ps.executeUpdate();
        return rows >0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        return false;
    }
    public static String getFullname(String username)
    {
         String query = "SELECT Full_Name FROM Customer WHERE Username= ?";

        try (
            Connection conn = DBConnection.connectDB();
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getString("Full_Name");


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
     public static int getID(String username)
    {
         String query = "SELECT Customer_ID FROM Customer WHERE Username= ?";

        try (
            Connection conn = DBConnection.connectDB();
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getInt("Customer_ID");


        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }


    

}
