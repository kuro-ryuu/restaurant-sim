package model;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.Optional;

public class RestaurantModel {
    private final List<Cashier> orderingCounters = new ArrayList<>();
    private final List<FoodStation> foodStations = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final Menu menu = new Menu();

    public RestaurantModel() {
    }

    public RestaurantModel(int cashierCount, int stationCount) {
        initialize(cashierCount, stationCount);
    }

    public void initialize(int cashierCount, int stationCount) {
        orderingCounters.clear();
        foodStations.clear();
        customers.clear();
        orders.clear();

        for (int i = 0; i < cashierCount; i++) {
            orderingCounters.add(new Cashier());
        }
        for (int i = 0; i < stationCount; i++) {
            foodStations.add(new FoodStation());
        }
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

    public Optional<FoodStation> findAvailableFoodStation() {
        return foodStations.stream().filter(station -> !station.isBusy()).findFirst();
=======

public class RestaurantModel {
    private List<Cashier> cashiers;
    private List<FoodStation> foodStations;
    private List<Customer> customers;
    private List<Order> orders;
    public RestaurantModel() {
        cashiers = new ArrayList<>();
        foodStations = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();
>>>>>>> 73e6216 (add new cashiers, foodStations, customers and orders list to the RestaurantModel class)
    }
}

