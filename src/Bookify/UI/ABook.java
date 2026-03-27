package Bookify.UI;

import javafx.scene.control.TableView;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ABook {
     private static Scene scene;
    private static Scenemanager manager;

    public ABook(Scenemanager manager)
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
        csaleText.setText("Total Books");
        csaleText.setFont(Font.font(null, FontWeight.BOLD, 15));
        csaleText.setFill(Color.WHITESMOKE);


         Text saleText = new Text();
        saleText.setText(String.valueOf(BookDAO.getBooks()));
        saleText.setFont(Font.font(null, FontWeight.BOLD, 15));
        saleText.setFill(Color.WHITESMOKE);


         VBox orderBox = new VBox();
        orderBox.setSpacing(15);                     // space between children
        orderBox.setAlignment(Pos.CENTER);           // center everything
        orderBox.setPadding(new Insets(40));
        orderBox.setStyle("-fx-background-color: #1E293B;");
        orderBox.prefWidthProperty().bind(root.widthProperty().multiply(0.1));


         Text corderText = new Text();
        corderText.setText("Total Stock");
        corderText.setFont(Font.font(null, FontWeight.BOLD, 15));
        corderText.setFill(Color.WHITESMOKE);


         Text orderText = new Text();
        orderText.setText(String.valueOf(BookDAO.getStock()));
        orderText.setFont(Font.font(null, FontWeight.BOLD, 15));
        orderText.setFill(Color.WHITESMOKE);

        VBox orderitemBox = new VBox();
        orderitemBox.setSpacing(15);                     // space between children
        orderitemBox.setAlignment(Pos.CENTER);           // center everything
        orderitemBox.setPadding(new Insets(40));
        orderitemBox.setStyle("-fx-background-color: #1E293B;");
        orderitemBox.prefWidthProperty().bind(root.widthProperty().multiply(0.1));


         Text corderitem = new Text();
        corderitem.setText("Printed Books");
        corderitem.setFont(Font.font(null, FontWeight.BOLD, 15));
        corderitem.setFill(Color.WHITESMOKE);


         Text orderitem = new Text();
        orderitem.setText(String.valueOf(BookDAO.getPrinted()));
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





            VBox box = new VBox();
              box.setStyle("""
            -fx-background-color: #3b4a5fff  ;
        """);

        box.setPadding(new Insets(30));
        box.setSpacing(50);
       
        

         Button addemployee = new Button("Add Book");
        addemployee.setPrefWidth(150);
        addemployee.setTextFill(Color.BLACK);
        addemployee.setFont(Font.font("Bold", 15));
        addemployee.setStyle("-fx-background-color: #e9b312;");
        addemployee.setCursor(Cursor.HAND);
        box.setMargin(addemployee, new Insets(0, 0, 0, 20));
        addemployee.setOnAction(e->{manager.addbook();});
        
        // remove employee by id 



        Text numText = new Text();
    numText.setText("BOOK ID");
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
        Button remployee = new Button("Remove Book");
        remployee.setPrefWidth(150);
        remployee.setTextFill(Color.BLACK);
        remployee.setFont(Font.font("Bold", 15));
        remployee.setStyle("-fx-background-color: #e9b312;");
        remployee.setCursor(Cursor.HAND);
        box.setMargin(remployee, new Insets(0, 0, 0, 20));
        remployee.setOnAction(e->{
            if (
            BookDAO.removeBook(Integer.valueOf(numField.getText())))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Book removed succefully");
                alert.showAndWait();
                return;
            }
            else
                {
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Failed");
                alert.setHeaderText(null);
                alert.setContentText("Couldnt remove Book");
                alert.showAndWait();
                return;
                }
        });
        
           HBox remployeeBox = new HBox();
        remployeeBox.setPadding(new Insets(15));
        remployeeBox.setSpacing(20);
        remployeeBox.setAlignment(Pos.CENTER_LEFT);

        remployeeBox.getChildren().addAll(numText,numField,remployee);


         Button vemployee = new Button("View Books");
        vemployee.setPrefWidth(150);
        vemployee.setTextFill(Color.BLACK);
        vemployee.setFont(Font.font("Bold", 15));
        vemployee.setStyle("-fx-background-color: #e9b312;");
        vemployee.setCursor(Cursor.HAND);
        box.setMargin(remployee, new Insets(0, 0, 0, 20));
        vemployee.setOnAction(e->{
            BorderPane tablePanel = BookTableView.createBookTableWithBackButton(manager);
            root.setCenter(tablePanel);
        });




        Text bookidtxt = new Text();
         bookidtxt.setText("change stock by id");
        bookidtxt.setFont(Font.font("Bold",20));
        bookidtxt.setFill(Color.WHITESMOKE);

            TextField bookid = new TextField();
bookid.setPromptText("Book ID");
bookid.setPrefWidth(120);
bookid.setPrefHeight(35);

bookid.setTextFormatter(new TextFormatter<>(change -> {
    if (change.getControlNewText().matches("\\d*")) {
        return change;
    }
    return null;
}));

Button changeStockBtn = new Button("Change Stock");
changeStockBtn.setPrefWidth(150);
changeStockBtn.setPrefHeight(35);
changeStockBtn.setFont(Font.font("Bold", 14));
changeStockBtn.setStyle("-fx-background-color: #e9b312;");
changeStockBtn.setCursor(Cursor.HAND);

HBox changeStockBox = new HBox(15);
changeStockBox.setAlignment(Pos.CENTER_LEFT);
changeStockBox.getChildren().addAll(bookid, changeStockBtn);


Text stockTxt = new Text("Stock Number");
stockTxt.setFont(Font.font("Bold", 18));
stockTxt.setFill(Color.WHITESMOKE);
stockTxt.setVisible(false);


TextField stockField = new TextField();
stockField.setPromptText("Stock");
stockField.setPrefWidth(120);
stockField.setPrefHeight(35);

stockField.setTextFormatter(new TextFormatter<>(change -> {
    if (change.getControlNewText().matches("\\d*")) {
        return change;
    }
    return null;
}));


Button confirmBtn = new Button("Confirm");
confirmBtn.setPrefWidth(150);
confirmBtn.setPrefHeight(35);
confirmBtn.setFont(Font.font("Bold", 14));
confirmBtn.setStyle("-fx-background-color: #e9b312;");
confirmBtn.setCursor(Cursor.HAND);


changeStockBtn.setOnAction(e -> {
    stockTxt.setVisible(true);
    stockField.setVisible(true);
    confirmBtn.setVisible(true);
});


confirmBtn.setOnAction(e -> {
    if (bookid.getText().isEmpty() || stockField.getText().isEmpty()) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("Enter Book ID and Stock");
        a.showAndWait();
        return;
    }

    boolean ok = BookDAO.updateBookStock(
        Integer.valueOf(bookid.getText()),
        Integer.valueOf(stockField.getText())
    );

    Alert alert = new Alert(
        ok ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR
    );
    alert.setHeaderText(null);
    alert.setContentText(
        ok ? "Stock updated successfully" : "Failed to update stock"
    );
    alert.showAndWait();
});

HBox stockBox = new HBox(15);
stockBox.setAlignment(Pos.CENTER_LEFT);
stockBox.getChildren().addAll(stockTxt, stockField, confirmBtn);
stockBox.setVisible(false);
changeStockBtn.setOnAction(e -> {
    stockBox.setVisible(true);
});


        box.getChildren().addAll(
    header,
    addemployee,
    remployeeBox,
    vemployee,
    bookidtxt,
    changeStockBox,
    stockBox
);



        root.setTop(vl);
        root.setCenter(box);
        Scene scene = new Scene(root);
        return scene;



    }

}
