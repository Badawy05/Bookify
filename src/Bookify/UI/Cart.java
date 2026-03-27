package Bookify.UI;

import java.util.ArrayList;

import Bookify.DAO.OrdersDAO;
import Bookify.Model.Book;
import Bookify.Model.Customer;
import Bookify.controllers.Scenemanager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Cart {
        final private Scenemanager manager;
    
        public Cart(Scenemanager manager)
        {
            this.manager=manager;
        }

        
        
        
     public Scene getcart(Customer cust)
    {  
        
        ArrayList<Book> cart0=cust.getCartItems();
        //cards pane
        VBox cards=new VBox(20);
        //Buttons pane and centralize
        VBox Buttons=new VBox(150);
        Buttons.setAlignment(Pos.CENTER);
        VBox.setMargin(Buttons, new Insets(0,0,0,0));
        //all pane contain all panes
        BorderPane all=new BorderPane();
        all.setMargin(Buttons, new Insets(0,100,0,0));
        //make books cards and add it to HBox pane then add it to VBoxpane
        for(Book b:cart0)
        {
            cards.getChildren().add(createBookCard(b, cust,manager));

        }
        //all.setRight(Buttons);
        //all.setLeft(cards);
        
        //scroll pane 
        //ScrollPane scroll= new ScrollPane(cards);
        //scroll.setFitToWidth(true);
        //scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        ScrollPane scroll = new ScrollPane(cards);
scroll.setFitToWidth(false);
scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
scroll.setStyle("-fx-background: #0f172A;");

       all.setCenter(scroll);   
all.setRight(Buttons);  


        
        

        //total textfield       
        double total=totalprice(cart0);
        Label totalp=new Label("total="+"$"+Math.ceil(total));
        totalp.setStyle("-fx-background-color:#1E293B;-fx-text-fill:white;-fx-font-weight:Bold;-fx-background-radius: 10;-fx-font-size: 30px;");
        totalp.setPadding(new Insets(20));
        Buttons.getChildren().add(totalp);

        //checkout button
        Button checkout=new Button("Check out");
        checkout.setStyle("-fx-background-color:#e9b312;-fx-font-size: 20px; -fx-font-weight: bold;-fx-background-radius: 25;");
        checkout.setPrefWidth(300);
        Buttons.getChildren().add(checkout);
        checkout.setOnAction(e->{
            manager.showreceipt();
        });

        //back button
        Button back=new Button("Back");
       back.setStyle("-fx-background-color:#e9b312;-fx-font-size: 20px; -fx-font-weight: bold;-fx-background-radius: 25;");
        back.setPrefWidth(300);
        back.setOnAction(e->manager.showstore());   
        Buttons.getChildren().add(back);
        

        

        cards.setStyle("-fx-background-color: #0f172A;");
        //scroll.setStyle("-fx-background-color: #0f172A;");
       all.setStyle("-fx-background-color: #0f172A;");

        Scene cartview=new Scene(all);
        
        return cartview;




        
    }


    private static HBox createBookCard(Book book,Customer cust,Scenemanager manager)
    {
        ImageView img = new ImageView(new Image(StorePage.class.getResourceAsStream("/" +book.getImagePath())));
        img.setFitWidth(200);
        img.setFitHeight(220);
        img.setPreserveRatio(true);
    

    Text title = new Text();
        title.setText(book.getTitle());
        title.setFont(Font.font("Bold",18));
        title.setFill(Color.WHITESMOKE);
        

    
        Text price = new Text();
        price.setText(String.valueOf("$ " +book.getPrice()));
        price.setFont(Font.font("Bold",15));
        price.setFill(Color.WHITESMOKE);

        Image trash=new Image("/images/trash.png");
        ImageView trash1=new ImageView(trash);
        trash1.setFitHeight(20);
        trash1.setFitWidth(20);
        trash1.setOnMouseClicked(e->{
            cust.removeFromCart(book);
            manager.showcart();}
        );
        
        

        






    HBox card = new HBox(50, img, title, price,trash1);
    card.setPadding(new Insets(10));
    card.setMinWidth(700);
    card.setMaxWidth(700);
    card.setStyle("-fx-background-color: #1E293B; -fx-background-radius: 10;");
    card.setAlignment(Pos.CENTER);
    return card;
    
    
    
}
public double totalprice(ArrayList<Book> cart)
{
    double totalp=0;
    for(Book b : cart)
    {
        totalp=totalp+b.getPrice();

    }


    return totalp;
}


}


