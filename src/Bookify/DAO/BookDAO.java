package Bookify.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bookify.Model.Book;
import Bookify.Model.EBook;
import Bookify.Model.PrintedBook;


public class BookDAO {
    public static boolean addBook(String Title,String Author,String Genre,int Published_Year,float Price,int Stock,String Type,String Image_Path,int Rate)
    {
        String query ="""
                INSERT INTO book
                (Title,Author,Genre,Published_Year,Price,Stock,Type,Image_Path,Rate)
                VALUES (?,?,?,?,?,?,?,?,?)
                """;
        try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        )
        {

        ps.setString(1, Title);
        ps.setString(2, Author);
        ps.setString(3, Genre);
        ps.setInt(4, Published_Year);
        ps.setFloat(5, Price);
        ps.setInt(6, Stock);
        ps.setString(7, Type);
        ps.setString(8, Image_Path);
        ps.setInt(9, Rate);

        int rows = ps.executeUpdate();
        return rows >0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean removeBook(int id)
    {
        String query= "DELETE FROM book WHERE Book_Id = ?";
         try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        )
        {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0; 
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public static boolean updateBookPrice(int id, float price) {
    String query = "UPDATE book SET Price = ? WHERE Book_Id = ?";
    try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        )
        {
            ps.setFloat(1, price);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            return rows > 0; 

        }
           catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateBookStock(int id, int stock) {
    String query = "UPDATE book SET Stock = ? WHERE Book_Id = ?";
    try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        )
        {
            ps.setInt(1, stock);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            return rows > 0; 

        }
           catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean decrementStock(int id) {
    String query = "UPDATE book SET Stock = Stock-1 WHERE Book_Id = ? and Stock >=1";
    try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        )
        {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0; 

        }
           catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean incrementStock(int id) {
    String query = "UPDATE book SET Stock = Stock+1 WHERE Book_Id = ?";
    try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        )
        {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0; 

        }
           catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ArrayList<Book> getAllBooks()
    {
        String query = "SELECT * FROM book";
        ArrayList<Book> books = new ArrayList<>();

        try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        
        )
        {
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Book book;
                if (rs.getString("Type").equalsIgnoreCase( "ebook"))
                    {
                         book = new EBook(rs.getInt("Book_ID"), rs.getString("Title"),
                         rs.getString("Author"),  rs.getString("Genre"),
                          rs.getInt("Published_Year"), rs.getDouble("Price"),
                           rs.getInt("Stock"),
                            rs.getString("Image_Path"), rs.getInt("Rate"));

                    }
                else
                    {
                         book = new PrintedBook(rs.getInt("Book_ID"), rs.getString("Title"),
                         rs.getString("Author"),  rs.getString("Genre"),
                          rs.getInt("Published_Year"), rs.getDouble("Price"),
                           rs.getInt("Stock"),
                            rs.getString("Image_Path"),rs.getInt("Rate"));
                    }
                    books.add(book);

            }
            return books;


        }
           catch (Exception e) {
            e.printStackTrace();
            return books;
           }
        }
        public static Book getBookById(int id)
        {
        String query = "SELECT * FROM book WHERE Book_Id = ?";
        Book book;              
        try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        
        )
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                {
            if (rs.getString("Type").equalsIgnoreCase( "ebook"))
                    {
                         book = new EBook(rs.getInt("Book_ID"), rs.getString("Title"),
                         rs.getString("Author"),  rs.getString("Genre"),
                          rs.getInt("Published_Year"), rs.getDouble("Price"),
                           rs.getInt("Stock"),
                            rs.getString("Image_Path"),rs.getInt("Rate"));
                            return book;


                    }
                else
                    {
                         book = new PrintedBook(rs.getInt("Book_ID"), rs.getString("Title"),
                         rs.getString("Author"),  rs.getString("Genre"),
                          rs.getInt("Published_Year"), rs.getDouble("Price"),
                           rs.getInt("Stock"),
                            rs.getString("Image_Path"),rs.getInt("Rate"));
                            return book;

                    }

            }
            return null;


        }

            catch (Exception e) {
            e.printStackTrace();
            return null;
           }
        }


          public static int getBooks()
    
    {
         String query = "SELECT count(*) as TR FROM Book";
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

    public static int getStock()
    
    {
         String query = "SELECT Stock FROM Book";
        try(
        Connection conn=DBConnection.connectDB();
        PreparedStatement ps=conn.prepareStatement(query);
        
        )
        {
            int total=0;
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                {
                    total+= rs.getInt("Stock");
                }
                return total;
    }
     catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    } 

     public static int getPrinted()
    
    {
         String query = """
         SELECT count(*) as TR FROM Book where Type = "printed" """;
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
