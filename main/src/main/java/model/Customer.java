package model;

public class Customer {
    private static int idCounter = 1;
    private final int id;
    private CustomerState state;
    private final long arrivalTime;
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

    public void setOrder(Order order) {
        this.order = order;
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

    public long getCashierServiceStartTime() {
        return cashierServiceStartTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Customer " + id;
    }
}
