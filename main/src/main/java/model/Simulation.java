package model;

import java.util.ArrayDeque;
import java.util.Queue;

public class Simulation {
    private final Queue<Customer> customerQueue = new ArrayDeque<>();
    private final Queue<Order> orderQueue = new ArrayDeque<>();
    long simulationClock = 0;
}

// TO DO: customerQueue, orderQueue, simulationClock
