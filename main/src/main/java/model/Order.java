package model;
import java.util.PriorityQueue;

public class Order {
    private double id;
    private PriorityQueue<Customer> orderList;
    private boolean orderCompletion = false;
    public Order() {
        this.id = id;
        orderList = new PriorityQueue<Customer>();
        this.orderCompletion = orderCompletion;
    }
    public double getId() {
        return id;
    }
    public boolean getOrderCompletion() {
        return orderCompletion;
    }
    public void addCustomer(Customer customer) {
        orderList.add(customer);
    }
}

// TO DO: id, customer, menuItems, completed