package Bookify.UI;
import java.util.ArrayList;

import Bookify.DAO.BookDAO;
import Bookify.Model.Book;
import Bookify.Model.Customer;
import Bookify.controllers.Scenemanager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;


public class StorePage {
    private  Scene scene;
    private final Scenemanager manager;
     public StorePage(Scenemanager manager)
    {
        this.manager=manager;
    }
    public Scene loadPage(Customer cust)
    {
        BorderPane root = new BorderPane();
       
      
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #0F172A;"); 
        
        Image icon = new Image(SignUpPage.class.getResourceAsStream("/images/Main_logo.png"));   
       
        
        // Header as Hbox

        HBox header = new HBox();
        header.setPadding(new Insets(15));
        header.setSpacing(20);
        header.setAlignment(Pos.CENTER);

         Text welText = new Text();
        welText.setText("Welcome, "+ manager.getCurrentUserFullName().trim().split("\\s+")[0]);
        welText.setFont(Font.font("Bold",30));
        welText.setFill(Color.WHITESMOKE);

        Text text = new Text();
        text.setText(" Bookify ");
        text.setFont(Font.font("Bold",50));
        text.setFill(Color.WHITESMOKE);

        TextField searchText = new TextField();
        searchText.setPromptText("Search");
        HBox.setHgrow(searchText, Priority.ALWAYS);
        searchText.setMaxWidth(500);
        HBox.setMargin(searchText, new Insets(20, 0, 0, 0));



        ImageView logo = new ImageView(icon);
        logo.setScaleX(0.6);
        logo.setScaleY(0.6);
        HBox.setMargin(logo, new Insets(-10, 0, 0, 0));

        Image cartimage = new Image(SignUpPage.class.getResourceAsStream("/images/Cart.png"));   
        ImageView cart = new ImageView(cartimage);
        cart.setScaleX(0.5);
        cart.setScaleY(0.5);
        cart.setCursor(Cursor.HAND);
        HBox.setMargin(cart, new Insets(10, 0, 0, 250));
        cart.setOnMouseClicked(e->manager.showcart());


        Button searchBtn = new Button("Search");
        searchBtn.setPrefWidth(100);
        searchBtn.setTextFill(Color.BLACK);
        searchBtn.setFont(Font.font("Bold", 16));
        searchBtn.setStyle("-fx-background-color: #e9b312;");
        HBox.setMargin(searchBtn, new Insets(15, 0, 0, 0));
        Button back = new Button("Back");
        back.setPrefWidth(50);
        back.setTextFill(Color.WHITESMOKE);
        back.setFont(Font.font("Bold", 16));
        back.setStyle("-fx-background-color: #eb1010ff;");
        HBox.setMargin(back, new Insets(5, 0, 0, 0));
        back.setOnAction(e->{
            manager.showlogin();
        });
        header.getChildren().addAll(welText,text,logo,searchText,searchBtn,back,cart);







      


        // ---------------------------------------------------------------------------------------------

        // center ( main table )

        ArrayList<Book> B=BookDAO.getAllBooks();
        int row=0;
        int column =0;
        GridPane grid = new GridPane();
        grid.setHgap(15);      // horizontal space between columns
        grid.setVgap(15);      // vertical space between rows
        grid.setAlignment(Pos.CENTER);


        ScrollPane scrollPane = new ScrollPane(grid);
    scrollPane.setFitToWidth(true);
    scrollPane.setPannable(true);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setStyle("""
    -fx-background-color: transparent;
    -fx-background: transparent;
""");


       for (int i = 0; i < B.size(); i++)
            {
                grid.add(createBookCard(B.get(i),cust),column,row);
                column +=1;
                if ( column ==3)
                    {
                        row++;
                        column=0;
                    }
            }

        // handle
        searchBtn.setOnAction(e -> {
    String keyword = searchText.getText().trim();

    if (keyword.isEmpty()) {
        populateGrid(grid, B,cust);   // show all books again
    } else {
        ArrayList<Book> filtered = searchBooks(keyword, B);
        populateGrid(grid, filtered,cust);
    }
});
searchBtn.setCursor(Cursor.HAND);

 root.setTop(header);
root.setCenter(scrollPane);
scene = new Scene(root,Color.web("#0F172A"));
return scene;

    }



    public Scene getScene()
    {
        return scene;
    }


    private static VBox createBookCard(Book book,Customer cust)
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
        


        

        HBox Hrate = new HBox();
        Hrate.setPadding(new Insets(15));
        Hrate.setSpacing(5);
        Hrate.setAlignment(Pos.CENTER);

        Image filledRate = new Image(SignUpPage.class.getResourceAsStream("/images/star_filled.png")); 
 Image unfilledRate = new Image(SignUpPage.class.getResourceAsStream("/images/star_unfilled.png")); 

    ImageView vfilledRate = new ImageView(filledRate);
        vfilledRate.setFitWidth(10);
        vfilledRate.setFitHeight(10);
        vfilledRate.setPreserveRatio(true);

        ImageView vunfilledRate = new ImageView(unfilledRate);
        vunfilledRate.setFitWidth(10);
        vunfilledRate.setFitHeight(10);
        vunfilledRate.setPreserveRatio(true);

        
        

     
for (int i = 0; i < 5; i++) {
    ImageView star;
    if (i < book.getRate()) {
        star = new ImageView(filledRate);
    } else {
        star = new ImageView(unfilledRate);
    }
    star.setFitWidth(18);
    star.setFitHeight(18);
    Hrate.getChildren().add(star);
}



        

        Button viewBtn = new Button("Add to Cart");
        viewBtn.setPrefWidth(150);
        viewBtn.setTextFill(Color.BLACK);
        viewBtn.setFont(Font.font("Bold", 15));
        viewBtn.setStyle("-fx-background-color: #e9b312;");
        viewBtn.setCursor(Cursor.HAND);
        viewBtn.setOnAction(e->cust.addToCart(book));



    VBox card = new VBox(10, img, title, price, Hrate,viewBtn);
    card.setPadding(new Insets(10));
    card.setStyle("-fx-background-color: #1E293B; -fx-background-radius: 10;");

    return card;
}


   private static ArrayList<Book> searchBooks(String keyword, ArrayList<Book> books) {
    ArrayList<Book> result = new ArrayList<>();

    for (Book b : books) {
        if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
            result.add(b);
        }
    }
    return result;
}


private static void populateGrid(GridPane grid, ArrayList<Book> books,Customer cust) {
    grid.getChildren().clear();

    int row = 0;    
    int column = 0;

    for (Book book : books) {
        grid.add(createBookCard(book,cust), column, row);
        column++;
        if (column == 3) {
            column = 0;
            row++;
        }
    }
}

}
