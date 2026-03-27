package Bookify.Model;

import java.util.ArrayList;

public class Customer implements User {
    private int id;
    public int getId() {
        return id;
    }
    private int Quantity;
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setTotal(int total) {
        Total = total;
    }
    public void setid(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getTotal() {
        return Total;
    }
    private int Total;
    private String name;
    private String email;
    private ArrayList<Book> cart = new ArrayList<>(); // Generic cart

    public Customer(String name) {
        this.name = name;
    }

    public void addToCart(Book book) {
        cart.add(book);
        System.out.println(book.getTitle() + " added to cart.");
    }

    public void removeFromCart(Book book) {
        if (cart.remove(book)) {
            System.out.println(book.getTitle() + " removed from cart.");
        } else {
            System.out.println(book.getTitle() + " not in cart.");
        }
    }

    public void checkout() {
        double total = 0;
        for (Book book : cart) {
            total += book.discountedPrice();
        }
        System.out.println("Total amount: $" + total);
        cart.clear();
    }

    public ArrayList<Book> getCartItems() {
        return cart;
    }

    public String getName() { return name; }

    @Override
    public void login() {
        System.out.println(name + " logged in.");
    }

    @Override
    public void logout() {
        System.out.println(name + " logged out.");
    }
}
