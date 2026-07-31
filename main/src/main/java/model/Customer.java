package model;

public class Customer {
    private static int idCounter = 1;
    private int id;
    private boolean arrivalState = false;
    private long arrivalTime;
    private long departureTime;
    public Customer() {
        this.id = idCounter++;
        this.arrivalState = arrivalState;
    }
}

// TO DO: id, state, arrivalTime, departureTime