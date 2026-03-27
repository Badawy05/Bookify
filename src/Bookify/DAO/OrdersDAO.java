package Bookify.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import Bookify.Model.Book;
import Bookify.Model.Customer;

public class OrdersDAO {
    private static int createNewOrder(int Customer_ID,int Total_Amount)
    {
         String query ="""
                INSERT INTO orders
                (Customer_ID,Total_Amount)
                VALUES (?,?)
                """;
        try (Connection conn = DBConnection.connectDB();
         PreparedStatement ps = conn.prepareStatement(
                 query, Statement.RETURN_GENERATED_KEYS)) {
        {
            ps.setInt(1, Customer_ID);
            ps.setInt(2,Total_Amount);

        
         if (ps.executeUpdate()==1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if ( rs.next())
                    {
                        return rs.getInt(1);
                    }
            }
        }
    }

        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    private static boolean AddOrderItems(int Order_ID,int Book_ID,int Quantity,double Price)
    {
         String query ="""
                INSERT INTO order_items
                (Order_ID,Book_ID,Quantity,Price)
                VALUES (?,?,?,?)
                """;
        try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        )
        {
            ps.setInt(1, Order_ID);
            ps.setInt(2,Book_ID);
            ps.setInt(3,Quantity);
            ps.setDouble(4,Price);


        
        int rows = ps.executeUpdate();
        return rows >0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
    
    public static void Order(Customer c)
    {

        int n=createNewOrder(c.getId(),c.getTotal());
        for ( int i =0;i<c.getCartItems().size();i++)
            {
                AddOrderItems(n, c.getCartItems().get(i).getId(), 1,c.getCartItems().get(i).getPrice());
            }
    }
    public static double totalSales()
    {
         String query = "SELECT Price, Quantity FROM order_Items";
        try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        
        )
        {
            double price=0.0;
            double total=0.0;
            int q=0;
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                {
                    price=rs.getDouble("Price");
                    q=rs.getInt("Quantity");
                    total+=price*q;
                }
                return total;
    }
     catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
    public static int totalOrders()
    {
         String query = "SELECT count(*) as TR FROM Orders";
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
     public static int totalItemOrders()
    {
         String query = "SELECT count(*) as TR FROM Order_items";
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

}