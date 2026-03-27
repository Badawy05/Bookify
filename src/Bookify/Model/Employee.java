package Bookify.Model;
public class Employee extends Person {

    protected String role;

    public Employee(String name, String role) {
        super(name);
        this.role = role;
    }

    public void displayEmployeeInfo() {
        System.out.println("name: " + name + ", Role: " + role);
    }
}
