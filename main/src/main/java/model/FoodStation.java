package model;

import java.util.Queue;
import java.util.LinkedList;

public class FoodStation {
    private int id;
    private static int idCounter = 1;
    private boolean busy;
    private Queue<Customer> queue;
    private long totalBusyTime;
    private long busyStartTime;
    private Customer currentCustomer;
    
    public FoodStation() {
        this.id = idCounter++;
        this.busy = false;
        this.queue = new LinkedList<>();
        this.totalBusyTime = 0;
    }
    public int getId() {
        return id;
    }
    public boolean isBusy() {
        return busy;
    }
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }
    public Queue<Customer> getQueue() {
        return queue;
    }
    public long getTotalBusyTime() {
        return totalBusyTime;
    }
    public void enqueue(Customer customer) {
        queue.add(customer);
        customer.setState(CustomerState.WAITING_IN_CASHIER_QUEUE);
    }
    public Customer dequeue() {
        return queue.poll();
    }
    public Customer peek() {
        return queue.peek();
    }
    public void startService(Menu task, MenuItem item.getPrepTime) {
        this.busy = true;
        this.currentCustomer = customer;
        this.busyStartTime = time;
        customer.setState(CustomerState.BEING_SERVED);
        customer.setCashierServiceStartTime(time);
    }
}

// TO DO: id, busy, currentOrder