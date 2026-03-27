package Bookify.Model;
public class PrintedBook extends Book {

    public PrintedBook(int id, String title, String author, String genre,
                int publishedYear, double price, int stock,
                 String imagePath,int rate) {
        super(
            id,
            title,
            author,
            genre,
            publishedYear,
            price,
            stock,     
            imagePath,
            rate
        );
        }

    @Override
    public double discountedPrice() {
        return getPrice() * 0.85;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
    }
}
