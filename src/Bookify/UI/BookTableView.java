package Bookify.UI;

import Bookify.DAO.BookDAO;
import Bookify.Model.Book;
import Bookify.controllers.Scenemanager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

public class BookTableView {

    /**
     * Creates and returns a TableView with a back button
     * @param manager Scenemanager for navigation
     * @return BorderPane containing the table and back button
     */
    @SuppressWarnings("unchecked")
    public static BorderPane createBookTableWithBackButton(Scenemanager manager) {
        BorderPane root = new BorderPane();
        
        // Create table
        TableView tableView = createBookTable();
        root.setCenter(tableView);
        
        // Create back button
        HBox bottomBox = new HBox();
        bottomBox.setPadding(new Insets(10));
        bottomBox.setSpacing(10);
        
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-padding: 10px 30px; -fx-font-size: 14;");
        backButton.setOnAction(event -> manager.abook());
        
        bottomBox.getChildren().add(backButton);
        root.setBottom(bottomBox);
        
        return root;
    }

    /**
     * Creates and returns a TableView populated with book data from BookDAO
     * @return TableView displaying all books
     */
    @SuppressWarnings("unchecked")
    public static TableView createBookTable() {
        TableView tableView = new TableView();
        
        // Create table columns
        TableColumn idColumn = new TableColumn("Book ID");
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        idColumn.setPrefWidth(80);
        
        TableColumn titleColumn = new TableColumn("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("title"));
        titleColumn.setPrefWidth(180);
        
        TableColumn authorColumn = new TableColumn("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory("author"));
        authorColumn.setPrefWidth(140);
        
        TableColumn genreColumn = new TableColumn("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory("genre"));
        genreColumn.setPrefWidth(120);
        
        TableColumn yearColumn = new TableColumn("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory("publishedYear"));
        yearColumn.setPrefWidth(80);
        
        TableColumn priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        priceColumn.setPrefWidth(100);
        
        TableColumn stockColumn = new TableColumn("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory("stock"));
        stockColumn.setPrefWidth(80);
        
        TableColumn ratingColumn = new TableColumn("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory("rate"));
        ratingColumn.setPrefWidth(80);
        
        // Add columns to table
        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, 
                                      genreColumn, yearColumn, priceColumn, 
                                      stockColumn, ratingColumn);
        
        // Load data from database
        loadBooksData(tableView);
        
        // Styling
        tableView.setStyle("-fx-font-size: 12;");
        
        return tableView;
    }

    /**
     * Loads book data from BookDAO and populates the table
     * @param tableView The TableView to populate
     */
    @SuppressWarnings("unchecked")
    public static void loadBooksData(TableView tableView) {
        ArrayList<Book> bookList = BookDAO.getAllBooks();
        ObservableList observableBookList = FXCollections.observableArrayList(bookList);
        tableView.setItems(observableBookList);
    }

    /**
     * Refreshes the table with latest data from database
     * @param tableView The TableView to refresh
     */
    public static void refreshTable(TableView tableView) {
        loadBooksData(tableView);
    }

    /**
     * Creates a customizable BookTableView with specific columns
     * @param columns The columns to display (e.g., "id", "title", "author", "price", "stock")
     * @return Configured TableView
     */
    @SuppressWarnings("unchecked")
    public static TableView createCustomBookTable(String... columns) {
        TableView tableView = new TableView();
        
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    TableColumn col = new TableColumn("Book ID");
                    col.setCellValueFactory(new PropertyValueFactory("id"));
                    col.setPrefWidth(80);
                    tableView.getColumns().add(col);
                }
                case "title" -> {
                    TableColumn col = new TableColumn("Title");
                    col.setCellValueFactory(new PropertyValueFactory("title"));
                    col.setPrefWidth(200);
                    tableView.getColumns().add(col);
                }
                case "author" -> {
                    TableColumn col = new TableColumn("Author");
                    col.setCellValueFactory(new PropertyValueFactory("author"));
                    col.setPrefWidth(150);
                    tableView.getColumns().add(col);
                }
                case "price" -> {
                    TableColumn col = new TableColumn("Price");
                    col.setCellValueFactory(new PropertyValueFactory("price"));
                    col.setPrefWidth(100);
                    tableView.getColumns().add(col);
                }
                case "stock" -> {
                    TableColumn col = new TableColumn("Stock");
                    col.setCellValueFactory(new PropertyValueFactory("stock"));
                    col.setPrefWidth(80);
                    tableView.getColumns().add(col);
                }
                case "genre" -> {
                    TableColumn col = new TableColumn("Genre");
                    col.setCellValueFactory(new PropertyValueFactory("genre"));
                    col.setPrefWidth(120);
                    tableView.getColumns().add(col);
                }
                case "year" -> {
                    TableColumn col = new TableColumn("Year");
                    col.setCellValueFactory(new PropertyValueFactory("publishedYear"));
                    col.setPrefWidth(80);
                    tableView.getColumns().add(col);
                }
                case "rating" -> {
                    TableColumn col = new TableColumn("Rating");
                    col.setCellValueFactory(new PropertyValueFactory("rate"));
                    col.setPrefWidth(80);
                    tableView.getColumns().add(col);
                }
            }
        }
        
        loadBooksData(tableView);
        tableView.setStyle("-fx-font-size: 12;");
        
        return tableView;
    }
}
