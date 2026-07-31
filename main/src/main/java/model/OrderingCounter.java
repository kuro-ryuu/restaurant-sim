package model;

public class OrderingCounter {
    private int id;
    private boolean busy;
    private Customer currentCustomer;

    public OrderingCounter(int id, boolean busy) {
        this.id = id;
        this.busy = false;
    }
    public boolean getbusy() {
        return busy;
    }
    public void setbusy(boolean busy) {
        this.busy = busy;
    }
    
}

// TO DO: id, busy, currentCustomer