package model;

public class Customer {
    private static int idCounter = 1;
    private final int id;
    private CustomerState state;
    private final long arrivalTime;
    private long departureTime;
    private Order order;
    private long cashierServiceStartTime;
    private double x;
    private double y;
    private boolean moving;
    private double destinationX;
    private double destinationY;
    private double speed = 3;

    public Customer(long arrivalTime) {
        this.id = idCounter++;
        this.state = CustomerState.ARRIVING;
        this.arrivalTime = arrivalTime;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setTarget(double x, double y) {
        destinationX = x;
        destinationY = y;
        moving = true;
    }

    public void updateCustomer() {
        if (!moving) {
            return;
        }
        double dx = destinationX - getX();
        double dy = destinationY - getY();
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        if (distance <= speed) {
            moving = false;
            setPosition(destinationX, destinationY);
        }
        else {
            double newX = getX() + speed;
            double newY = getY() + speed;
            setPosition(newX, newY);
        }
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
