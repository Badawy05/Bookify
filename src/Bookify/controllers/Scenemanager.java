package Bookify.controllers;

import Bookify.UI.AdminPage;
import Bookify.UI.Cart;
import Bookify.UI.Receipt;
import Bookify.UI.SignUpPage;
import Bookify.UI.StorePage;
import Bookify.UI.login_page;
import Bookify.UI.signin;
import Bookify.UI.ABook;
import Bookify.UI.AddBook;
import Bookify.UI.AddStaff;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import Bookify.DAO.CustomerDAO;
import Bookify.Model.Customer;

public class Scenemanager {
    private final Stage stage;
    private String currentUserFullName;
    private Customer cust;

    public Customer getCust() {
        return cust;
    }
    public String getCurrentUserFullName() {
        return currentUserFullName;
    }
    public void setCurrentUserFullName(String currentUserFullName) {
        this.currentUserFullName = currentUserFullName;
        cust=new Customer(currentUserFullName);
    }
    public void setCurrentID(String Username) {
        cust.setid(CustomerDAO.getID(Username));
    }
    public Scenemanager(Stage stage)
    {
        this.stage=stage;
        Image icon = new Image(SignUpPage.class.getResourceAsStream("/images/Main_logo.png"));   
        stage.getIcons().add(icon);
         stage.setWidth(1500);
        stage.setHeight(800);

        stage.setResizable(false);
    
        stage.setTitle("Bookify"); // title
    }
    public void showlogin()
    {
        stage.setScene(new login_page(this).getscene());
       
    }
        public void showsignin()
    {
        stage.setScene(new signin(this).getscene());
        
    }
     public void showstore()
    {
        stage.setScene(new StorePage(this).loadPage(cust));

        
    }
     public void showSignUp()
    {
         stage.setScene(new SignUpPage(this).loadPage());
    }
    public void showcart()
    {
        stage.setScene(new Cart(this).getcart(cust));
    }
     public void admin()
    {
        stage.setScene(new AdminPage(this).loadPage());
    }

     public void abook()
    {
        stage.setScene(new ABook(this).loadPage());
    }
     public void addbook()
    {
        stage.setScene(new AddBook(this).loadPage());
    }

    public void addstaff()
    {
        stage.setScene(new AddStaff(this).loadPage());
    }

    public void showreceipt()
    {
        stage.setScene(new Receipt(this).getReceipt(cust));
    }

}
