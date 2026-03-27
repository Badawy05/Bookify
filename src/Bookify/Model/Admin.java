package Bookify.Model;
import java.util.ArrayList;

public class Admin extends Employee implements User {
    public Admin(String name, String role) {
        super(name, role);
    }

    public void addBook(ArrayList<Book> books, Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added");
    }

    public void removeBook(ArrayList<Book> books, Book book) {
        books.remove(book);
        System.out.println(book.getTitle() + " removed ");
    }

    @Override
    public void login() {
        System.out.println(name + " Admin logged in");
    }

    @Override
    public void logout() {
        System.out.println(name + " Admin logged out");
    }
}
