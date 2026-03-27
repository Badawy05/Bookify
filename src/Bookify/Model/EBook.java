package Bookify.Model;
public class EBook extends Book{
    private double fileSize;

    public EBook (int id, String title, String author, String genre,
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
        return getPrice() * 0.9;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("file size: " + fileSize + "Mb");
    }
}
