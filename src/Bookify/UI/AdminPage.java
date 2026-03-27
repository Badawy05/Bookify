package Bookify.UI;

import Bookify.DAO.BookDAO;
import Bookify.DAO.OrdersDAO;
import Bookify.DAO.StaffDAO;
import Bookify.Model.Book;
import Bookify.controllers.Scenemanager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AdminPage {
    private static Scene scene;
    private static Scenemanager manager;

    public AdminPage(Scenemanager manager)
    {
        this.manager=manager;
    }
    public Scene loadPage()
    {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #0F172A;");

        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setSpacing(20);
        header.setAlignment(Pos.CENTER);


        VBox saleBox = new VBox();
        saleBox.setSpacing(15);                     // space between children
        saleBox.setAlignment(Pos.CENTER);           // center everything
        saleBox.setPadding(new Insets(40));
        saleBox.setStyle("-fx-background-color: #1E293B;");
        saleBox.prefWidthProperty().bind(root.widthProperty().multiply(0.1));


         Text csaleText = new Text();
        csaleText.setText("Total Sales");
        csaleText.setFont(Font.font(null, FontWeight.BOLD, 15));
        csaleText.setFill(Color.WHITESMOKE);


         Text saleText = new Text();
        saleText.setText(String.valueOf(OrdersDAO.totalSales()) + " $");
        saleText.setFont(Font.font(null, FontWeight.BOLD, 15));
        saleText.setFill(Color.WHITESMOKE);


         VBox orderBox = new VBox();
        orderBox.setSpacing(15);                     // space between children
        orderBox.setAlignment(Pos.CENTER);           // center everything
        orderBox.setPadding(new Insets(40));
        orderBox.setStyle("-fx-background-color: #1E293B;");
        orderBox.prefWidthProperty().bind(root.widthProperty().multiply(0.1));


         Text corderText = new Text();
        corderText.setText("Total Orders");
        corderText.setFont(Font.font(null, FontWeight.BOLD, 15));
        corderText.setFill(Color.WHITESMOKE);


         Text orderText = new Text();
        orderText.setText(String.valueOf(OrdersDAO.totalOrders()));
        orderText.setFont(Font.font(null, FontWeight.BOLD, 15));
        orderText.setFill(Color.WHITESMOKE);

        VBox orderitemBox = new VBox();
        orderitemBox.setSpacing(15);                     // space between children
        orderitemBox.setAlignment(Pos.CENTER);           // center everything
        orderitemBox.setPadding(new Insets(40));
        orderitemBox.setStyle("-fx-background-color: #1E293B;");
        orderitemBox.prefWidthProperty().bind(root.widthProperty().multiply(0.1));


         Text corderitem = new Text();
        corderitem.setText("Total Item Orders");
        corderitem.setFont(Font.font(null, FontWeight.BOLD, 15));
        corderitem.setFill(Color.WHITESMOKE);


         Text orderitem = new Text();
        orderitem.setText(String.valueOf(OrdersDAO.totalItemOrders()));
        orderitem.setFont(Font.font(null, FontWeight.BOLD, 15));
        orderitem.setFill(Color.WHITESMOKE);

        Line line = new Line();
        line.setStroke(Color.WHITESMOKE);
        line.setStrokeWidth(2);
        line.endXProperty().bind(root.widthProperty());
        
        saleBox.getChildren().addAll(csaleText,saleText);
        orderBox.getChildren().addAll(corderText,orderText);
        orderitemBox.getChildren().addAll(corderitem,orderitem);


        header.getChildren().addAll(saleBox,orderBox,orderitemBox);

        VBox vl = new VBox();
        vl.setSpacing(15);                     // space between children
        vl.setAlignment(Pos.CENTER);           // center everything
        vl.setPadding(new Insets(40));
        vl.getChildren().addAll(header,line);

        //-----------------------------------------------------------------------

        //left ppanel


        VBox leftPanel = new VBox(20);
        leftPanel.setPrefWidth(240);
        leftPanel.setPadding(new Insets(20));
        leftPanel.setStyle("""
            -fx-background-color: #334155  ;
        """);


        // staff q  
        HBox item1 = new HBox();
        item1.setPadding(new Insets(10, 15, 10, 15));
        item1.setAlignment(Pos.CENTER);
        item1.setStyle(" -fx-background-color: #475569  ;-fx-cursor: hand;");

        Text label = new Text("Staff");
        label.setFill(Color.WHITESMOKE);
        label.setFont(Font.font(null, FontWeight.BOLD, 18));

        item1.getChildren().add(label);

        item1.setOnMouseClicked(e -> {
                    root.setCenter(staff());
        });
        item1.setOnMouseEntered(e ->
    item1.setStyle("""
        -fx-background-color: #334155;
        -fx-cursor: hand;
    """)
);

item1.setOnMouseExited(e ->
    item1.setStyle("""
        -fx-background-color: #334155;
        -fx-cursor: hand;
    """)
);

// Book 
        HBox item2 = new HBox();
        item2.setPadding(new Insets(10, 15, 10, 15));
        item2.setAlignment(Pos.CENTER);
        item2.setStyle(" -fx-background-color: #475569  ;-fx-cursor: hand;");

        Text label2 = new Text("Books");
        label2.setFill(Color.WHITESMOKE);
        label2.setFont(Font.font(null, FontWeight.BOLD, 18));

        item2.getChildren().add(label2);

        item2.setOnMouseClicked(e -> { manager.abook();
        });
        item2.setOnMouseEntered(e ->
    item2.setStyle("""
        -fx-background-color: #334155;
        -fx-cursor: hand;
    """)
);

item2.setOnMouseExited(e ->
    item2.setStyle("""
        -fx-background-color: #334155;
        -fx-cursor: hand;
    """)
);



// staff 
        HBox item3 = new HBox();
        item3.setPadding(new Insets(10, 15, 10, 15));
        item3.setAlignment(Pos.CENTER);
        item3.setStyle(" -fx-background-color: #475569  ;-fx-cursor: hand;");

        Text label3 = new Text("Order");
        label3.setFill(Color.WHITESMOKE);
        label3.setFont(Font.font(null, FontWeight.BOLD, 18));

        item3.getChildren().add(label3);

        item3.setOnMouseClicked(e -> {
        });
        item3.setOnMouseEntered(e ->
    item3.setStyle("""
        -fx-background-color: #334155;
        -fx-cursor: hand;
    """)
);

item3.setOnMouseExited(e ->
    item3.setStyle("""
        -fx-background-color: #334155;
        -fx-cursor: hand;
    """)
);





        leftPanel.getChildren().addAll(item1,item2);
        
        Button backBtn = new Button("Logout");
        backBtn.setPrefWidth(200);
        backBtn.setTextFill(Color.WHITE);
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backBtn.setStyle("-fx-background-color: #fc0e0e; -fx-padding: 10px;");
        backBtn.setOnAction(e -> manager.showlogin());
        VBox.setMargin(backBtn, new Insets(20, 0, 0, 0));
        leftPanel.getChildren().add(backBtn);
        
        root.setTop(vl);
        root.setLeft(leftPanel);
        scene = new Scene(root,Color.web("#0F172A"));
        return scene;

    }










     private static VBox staff()
        {
            VBox box = new VBox();
              box.setStyle("""
            -fx-background-color: #3b4a5fff  ;
        """);

        box.setPadding(new Insets(30));
        box.setSpacing(50);
       
            HBox header = new HBox();
        header.setPadding(new Insets(15));
        header.setSpacing(20);
        header.setAlignment(Pos.CENTER);
        


        VBox saleBox = new VBox();
        saleBox.setSpacing(15);                     // space between children
        saleBox.setAlignment(Pos.CENTER);           // center everything
        saleBox.setPadding(new Insets(40));
        saleBox.setStyle("-fx-background-color: #1E293B;");

      

         Text csaleText = new Text();
        csaleText.setText("Total Employees");
        csaleText.setFont(Font.font(null, FontWeight.BOLD, 14));
        csaleText.setFill(Color.WHITESMOKE);


         Text saleText = new Text();
        saleText.setText(String.valueOf(StaffDAO.getEmployee()));
        saleText.setFont(Font.font(null, FontWeight.BOLD, 14));
        saleText.setFill(Color.WHITESMOKE);


         VBox orderBox = new VBox();
        orderBox.setSpacing(15);                     // space between children
        orderBox.setAlignment(Pos.CENTER);           // center everything
        orderBox.setPadding(new Insets(40));
        orderBox.setStyle("-fx-background-color: #1E293B;");


         Text corderText = new Text();
        corderText.setText("Admins Count");
        corderText.setFont(Font.font(null, FontWeight.BOLD, 14));
        corderText.setFill(Color.WHITESMOKE);


         Text orderText = new Text();
        orderText.setText(String.valueOf(StaffDAO.getAdmins()));
        orderText.setFont(Font.font(null, FontWeight.BOLD, 14));
        orderText.setFill(Color.WHITESMOKE);

        

        Line line = new Line();
        line.setStroke(Color.WHITESMOKE);
        line.setStrokeWidth(2);
        
        saleBox.getChildren().addAll(csaleText,saleText);
        orderBox.getChildren().addAll(corderText,orderText);
        
        
        header.getChildren().addAll(saleBox,orderBox);

        // ADD EMPLOYEE

         Button addemployee = new Button("Add Employee");
        addemployee.setPrefWidth(150);
        addemployee.setTextFill(Color.BLACK);
        addemployee.setFont(Font.font("Bold", 15));
        addemployee.setStyle("-fx-background-color: #e9b312;");
        addemployee.setCursor(Cursor.HAND);
        box.setMargin(addemployee, new Insets(0, 0, 0, 20));
        addemployee.setOnAction(e->{manager.addstaff();});
        
        // remove employee by id 



        Text numText = new Text();
    numText.setText("Employee ID");
    numText.setFont(Font.font("Bold",20));
    numText.setFill(Color.WHITESMOKE);

        TextField numField = new TextField();
        numField.setPromptText("Numbers only");

        numField.setTextFormatter(new TextFormatter<>(change -> {
    if (change.getControlNewText().matches("\\d*")) {
        return change;
    }
    return null;
}));
        Button remployee = new Button("Remove Employee");
        remployee.setPrefWidth(150);
        remployee.setTextFill(Color.BLACK);
        remployee.setFont(Font.font("Bold", 15));
        remployee.setStyle("-fx-background-color: #e9b312;");
        remployee.setCursor(Cursor.HAND);
        box.setMargin(remployee, new Insets(0, 0, 0, 20));
        remployee.setOnAction(e->{
            if (
            StaffDAO.removeEmployee(Integer.valueOf(numField.getText())))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Employee removed succefully");
                alert.showAndWait();
                return;
            }
            else
                {
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Failed");
                alert.setHeaderText(null);
                alert.setContentText("Couldnt remove Employee");
                alert.showAndWait();
                return;
                }
        });
        
           HBox remployeeBox = new HBox();
        remployeeBox.setPadding(new Insets(15));
        remployeeBox.setSpacing(20);
        remployeeBox.setAlignment(Pos.CENTER_LEFT);

        remployeeBox.getChildren().addAll(numText,numField,remployee);


         Button vemployee = new Button("View Employees");
        vemployee.setPrefWidth(150);
        vemployee.setTextFill(Color.BLACK);
        vemployee.setFont(Font.font("Bold", 15));
        vemployee.setStyle("-fx-background-color: #e9b312;");
        vemployee.setCursor(Cursor.HAND);
        box.setMargin(remployee, new Insets(0, 0, 0, 20));
        vemployee.setOnAction(e->{});


        box.getChildren().addAll(header,addemployee,remployeeBox);
        return box;

        }


}
