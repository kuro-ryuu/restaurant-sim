package model;

public class Customer {
    private static int idCounter = 1;
    private int id;
    private CustomerState state;
    private long arrivalTime;
    private long departureTime;
    private Order order;
    private long cashierServiceStartTime;
    public Customer(long arrivalTime) {
        this.id = idCounter++;
        this.state = CustomerState.ARRIVING;
        this.arrivalTime = arrivalTime;
    }
    public int getId() {
        return id;
    }
    public CustomerState getState() {
        return state;
    }
    public long getArrivalTime() {
        return arrivalTime;
    }
    public long getDepartureTime() {
        return departureTime;
    }
    public void setOrder(Order smth) {
        this.order = smth;
    }
    public Order getOrder() {
        return order;
    }
    public void setState(CustomerState state) {
        this.state = state;
    }
    public void setCashierServiceStartTime(long time) {
        this.cashierServiceStartTime = time;
    }
    public void setFoodPreparationStartTime(long time) {
        this.kitchenServiceStartTime = time;
    }
    @Override
    public String toString() {
        return "Customer " + id;
    }
}

// TO DO: id, state, arrivalTime, departureTime