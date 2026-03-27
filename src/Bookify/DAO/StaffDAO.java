package Bookify.DAO;
import java.sql.*;

import Bookify.Model.PasswordUtil;

public class StaffDAO {

    private static boolean checkPass(String username,String pass)
    {
          String query = "SELECT 1 FROM Staff WHERE Pass = ? and Username= ?";

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
         String query = "SELECT 1 FROM Staff WHERE Username= ?";

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
    public static boolean AddNewUser(String username,String pass,String Full_Name,String email,String phone,String role)
    {
        if (!userExists(username))
        {
        String query = """
        INSERT INTO staff 
        (Username,Pass,Full_Name,Email,Phone,Role) 
        values(?,?,?,?,?,?)""";
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
        ps.setString(6, role);
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

    public static int getAdmins()
    
    {
         String query = """
         SELECT count(*) as 
         TR FROM staff WHERE Role = "ADMIN" """;
        try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        
        )
        {
            
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                {
                    return rs.getInt("TR");
                }
                return 0;
    }
     catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    } 

        public static int getEmployee()
    
    {
         String query = "SELECT count(*) as TR FROM staff";
        try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        
        )
        {
            
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                {
                    return rs.getInt("TR");
                }
                return 0;
    }
     catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    } 

    public static boolean removeEmployee(int id)
    
    {
         String query = "Delete From staff  where Staff_ID = ?";
        try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        
        )
        {
            ps.setInt(1, id);
            int rs = ps.executeUpdate();
            System.out.println("Rows affected = " + rs);

           return rs >0;
    }
     catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    } 

    public static boolean checkAdmin(String username)
    {
          String query = "SELECT Role FROM Staff WHERE Username= ?";

        try (
            Connection conn = DBConnection.connectDB();
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getString("Role").toLowerCase().equals("ADMIN".toLowerCase());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
