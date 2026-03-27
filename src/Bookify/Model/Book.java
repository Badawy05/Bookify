package Bookify.Model;
public abstract class Book {

    // ===== Database fields =====
    private final int id;                // PK (auto-increment)
    private String title;
    private String author;
    private String genre;
    private int publishedYear;
    private double price;
    private int stock;
    private String imagePath;
    private int rate;

    // ===== Constructor =====
    public Book(int id, String title, String author, String genre,
                int publishedYear, double price, int stock, String imagePath,int rate) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
        this.rate=rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getPublishedYear() { return publishedYear; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getImagePath() { return imagePath; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public void displayInfo() {
        System.out.println(
            "ID: " + id +
            ", Title: " + title +
            ", Author: " + author +
            ", Price: $" + price +
            ", Stock: " + stock
        );
    }

    public abstract double discountedPrice();
}