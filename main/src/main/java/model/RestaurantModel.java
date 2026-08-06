package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantModel {
    private final List<Cashier> orderingCounters = new ArrayList<>();
    private final List<FoodStation> foodStations = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final Menu menu = new Menu();

    public void initialize(int cashierCount) {
        orderingCounters.clear();
        foodStations.clear();
        customers.clear();
        orders.clear();
        FoodStation.resetCounter();
        Cashier.resetCounter();

        for (int i = 0; i < cashierCount; i++) {
            orderingCounters.add(new Cashier());
        }
        foodStations.add(new FoodStation(StationType.CHICKEN));
        foodStations.add(new FoodStation(StationType.RICE));
        foodStations.add(new FoodStation(StationType.FRIES));
    }

    public List<Cashier> getOrderingCounters() {
        return orderingCounters;
    }

    public List<FoodStation> getFoodStations() {
        return foodStations;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Menu getMenu() {
        return menu;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Optional<Cashier> findAvailableCashier() {
        return orderingCounters.stream().filter(cashier -> !cashier.isBusy()).findFirst();
    }
    public FoodStation findStation(StationType type) {
        for (FoodStation foodStation: foodStations) {
            if (foodStation.getStationType() == type) {
                return foodStation;
            }
        }
        return null;
    }
}

