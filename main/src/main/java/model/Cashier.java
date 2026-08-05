package model;

import java.util.LinkedList;
import java.util.Queue;

public class Cashier {
    private int id;
    private boolean busy;
    private Customer currentCustomer;
    private Queue<Customer> queue;
    private long totalBusyTime;
    private long busyStartTime;

    public Cashier() {
        this.id = id;
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
    public void startService(Customer customer, long time) {
        this.busy = true;
        this.currentCustomer = customer;
        this.busyStartTime = time;
        customer.setState(CustomerState.BEING_SERVED);
        customer.setCashierServiceStartTime(time);
    }
}
