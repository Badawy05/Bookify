package Bookify.UI;

import java.util.ArrayList;

import Bookify.DAO.OrdersDAO;
import Bookify.Model.Book;
import Bookify.Model.Customer;
import Bookify.controllers.Scenemanager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Receipt {
    final private Scenemanager manager;

    public Receipt(Scenemanager manager) {
        this.manager = manager;
    }

    public Scene getReceipt(Customer cust) {
        ArrayList<Book> cartItems = cust.getCartItems();
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #A9A9A9;");
        root.setPadding(new Insets(40));

        // ===== HEADER =====
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        
        Text title = new Text("ORDER RECEIPT");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.BLACK);

        Text customerText = new Text("Customer: " + cust.getName());
        customerText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        customerText.setFill(Color.BLACK);

        Text dateText = new Text("Date: " + java.time.LocalDate.now());
        dateText.setFont(Font.font("Arial", 14));
        dateText.setFill(Color.BLACK);

        header.getChildren().addAll(title, customerText, dateText);
        root.setTop(header);

        // ===== ITEMS DETAILS =====
        VBox itemsContainer = new VBox(15);
        itemsContainer.setPadding(new Insets(20));
        itemsContainer.setStyle("-fx-background-color: #1E293B; -fx-background-radius: 10;");

        // Header row for items table
        HBox itemHeader = createItemHeaderRow();
        itemsContainer.getChildren().add(itemHeader);

        // Add each item with quantity and price
        double subtotal = 0;
        for (Book book : cartItems) {
            HBox itemRow = createItemRow(book);
            itemsContainer.getChildren().add(itemRow);
            subtotal += book.getPrice();
        }

        ScrollPane scrollPane = new ScrollPane(itemsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: #A9A9A9;");
        root.setCenter(scrollPane);

        // ===== TOTAL SECTION =====
        VBox totalSection = new VBox(15);
        totalSection.setPadding(new Insets(20));
        totalSection.setStyle("-fx-background-color: #1E293B; -fx-background-radius: 10;");
        totalSection.setAlignment(Pos.CENTER_RIGHT);

        HBox subtotalBox = new HBox(20);
        subtotalBox.setAlignment(Pos.CENTER_RIGHT);
        Text subtotalLabel = new Text("Subtotal:");
        subtotalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        subtotalLabel.setFill(Color.WHITESMOKE);
        Text subtotalValue = new Text("$" + String.format("%.2f", subtotal));
        subtotalValue.setFont(Font.font("Arial", 16));
        subtotalValue.setFill(Color.WHITESMOKE);
        subtotalBox.getChildren().addAll(subtotalLabel, subtotalValue);

        HBox totalBox = new HBox(20);
        totalBox.setAlignment(Pos.CENTER_RIGHT);
        Text totalLabel = new Text("TOTAL:");
        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        totalLabel.setFill(Color.web("#e9b312"));
        Text totalValue = new Text("$" + String.format("%.2f", subtotal));
        totalValue.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        totalValue.setFill(Color.web("#e9b312"));
        totalBox.getChildren().addAll(totalLabel, totalValue);

        totalSection.getChildren().addAll(subtotalBox, totalBox);

        // ===== BUTTONS =====
        VBox buttonSection = new VBox(15);
        buttonSection.setAlignment(Pos.CENTER);
        buttonSection.setPadding(new Insets(20));

        Button confirmBtn = new Button("Confirm & Complete Order");
        confirmBtn.setPrefWidth(250);
        confirmBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        confirmBtn.setStyle("-fx-background-color: #e9b312; -fx-text-fill: black; -fx-background-radius: 10;");
        confirmBtn.setOnAction(e -> {
            // Process the order
            OrdersDAO.Order(cust);
            cust.getCartItems().clear();
            manager.showstore();
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setPrefWidth(250);
        cancelBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        cancelBtn.setStyle("-fx-background-color: #fc0e0e; -fx-text-fill: white; -fx-background-radius: 10;");
        cancelBtn.setOnAction(e -> manager.showcart());

        buttonSection.getChildren().addAll(confirmBtn, cancelBtn);

        BorderPane bottomPane = new BorderPane();
        bottomPane.setCenter(totalSection);
        bottomPane.setBottom(buttonSection);
        bottomPane.setStyle("-fx-background-color: #A9A9A9;");

        root.setBottom(bottomPane);

        return new Scene(root);
    }

    private HBox createItemHeaderRow() {
        HBox header = new HBox(20);
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #334155; -fx-background-radius: 5;");

        Text bookTitle = new Text("Book Title");
        bookTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bookTitle.setFill(Color.WHITESMOKE);
        HBox.setHgrow(bookTitle, javafx.scene.layout.Priority.ALWAYS);

        Text quantity = new Text("Quantity");
        quantity.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        quantity.setFill(Color.WHITESMOKE);
        quantity.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Text price = new Text("Unit Price");
        price.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        price.setFill(Color.WHITESMOKE);
        price.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Text totalPrice = new Text("Total");
        totalPrice.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        totalPrice.setFill(Color.WHITESMOKE);
        totalPrice.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        header.getChildren().addAll(bookTitle, quantity, price, totalPrice);
        return header;
    }

    private HBox createItemRow(Book book) {
        HBox itemRow = new HBox(20);
        itemRow.setPadding(new Insets(12, 10, 12, 10));
        itemRow.setAlignment(Pos.CENTER_LEFT);
        itemRow.setStyle("-fx-background-color: #334155; -fx-background-radius: 5;");

        // Book title
        Text titleText = new Text(book.getTitle());
        titleText.setFont(Font.font("Arial", 13));
        titleText.setFill(Color.WHITESMOKE);
        HBox.setHgrow(titleText, javafx.scene.layout.Priority.ALWAYS);

        // Quantity (always 1 per item in current system)
        Text quantityText = new Text("1");
        quantityText.setFont(Font.font("Arial", 13));
        quantityText.setFill(Color.WHITESMOKE);
        HBox quantityBox = new HBox();
        quantityBox.setPrefWidth(100);
        quantityBox.setAlignment(Pos.CENTER);
        quantityBox.getChildren().add(quantityText);

        // Unit price
        Text priceText = new Text("$" + String.format("%.2f", book.getPrice()));
        priceText.setFont(Font.font("Arial", 13));
        priceText.setFill(Color.WHITESMOKE);
        HBox priceBox = new HBox();
        priceBox.setPrefWidth(100);
        priceBox.setAlignment(Pos.CENTER);
        priceBox.getChildren().add(priceText);

        // Total (quantity * price)
        double total = 1 * book.getPrice();
        Text totalText = new Text("$" + String.format("%.2f", total));
        totalText.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        totalText.setFill(Color.web("#e9b312"));
        HBox totalBox = new HBox();
        totalBox.setPrefWidth(100);
        totalBox.setAlignment(Pos.CENTER);
        totalBox.getChildren().add(totalText);

        itemRow.getChildren().addAll(titleText, quantityBox, priceBox, totalBox);
        return itemRow;
    }
}
