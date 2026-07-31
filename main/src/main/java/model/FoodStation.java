package model;
import java.util.PriorityQueue;

public class FoodStation {
    private double id;
    private boolean busy = false;
    private PriorityQueue<Customer> currentOrder = new PriorityQueue<>();
    public FoodStation() {
        this.id = id;
    }
    public void changeState() {
        this.busy = !busy;
    }
    public void prepareItem() {
        
    }
}

// TO DO: id, busy, currentOrder