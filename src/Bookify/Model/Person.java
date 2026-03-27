package Bookify.Model;
public class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public void displayPersonInfo() {
        System.out.println("name: " + name);
    }
}
