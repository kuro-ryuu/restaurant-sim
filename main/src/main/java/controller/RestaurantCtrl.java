package controller;

import javafx.fxml.FXML;
import model.*;
import view.RestaurantView;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RestaurantCtrl {
    private final RestaurantModel model;
    private final RestaurantView view;
    private long currentTime;
    private static final long CASHIER_SERVICE_TIME = 5;

    // STATISTICS ( for display at the end :) )
    private int totalServed = 0;
    private long totalResponseTime = 0;

    public RestaurantCtrl() {
        this(new RestaurantModel(), new RestaurantView());
    }

    public RestaurantCtrl(RestaurantModel model, RestaurantView view) {
        this.model = model;
        this.view = view;
        this.currentTime = 0;
    }

    public void initialize(int cashierCount) {
        model.initialize(cashierCount);
        currentTime = 0;
        refreshView();
    }

    public Customer createCustomer() {
        Customer customer = new Customer(currentTime);
        model.addCustomer(customer);
        return customer;
    }

    public Order placeOrder(Customer customer) {
        Order order = new Order(customer);
        List<MenuItem> items = model.getMenu().generateRandomOrder();
        items.forEach(order::addItem);
        customer.setOrder(order);
        model.addOrder(order);
        return order;
    }

    public void queueCustomer(Customer customer) {
        Optional<Cashier> cashier = model.getOrderingCounters().stream()
                .min(Comparator.comparingInt(c -> c.getQueue().size()));
        cashier.ifPresent(target -> target.enqueue(customer));
    }

    public void advanceTime(long ticks) {
        if (ticks <= 0) {
            return;
        }

        for (long i = 0; i < ticks; i++) {
            currentTime++;
            stepSimulation();
        }

        refreshView();
    }

    private void stepSimulation() {
        completeCashierServices();
        startCashierService();
        assignOrdersToStations();
        completeStationTasks();
        startStationTasks();
    }

    private void startCashierService() {
        for (Cashier cashier : model.getOrderingCounters()) {
            if (!cashier.isBusy() && !cashier.getQueue().isEmpty()) {
                Customer customer = cashier.dequeue();
                if (customer != null) {
                    cashier.startService(customer, currentTime);
                }
            }
        }
    }

    private void completeCashierServices() {
        for (Cashier cashier : model.getOrderingCounters()) {
            if (cashier.isBusy() && cashier.getCurrentCustomer() != null) {
                if (currentTime - cashier.getBusyStartTime() >= CASHIER_SERVICE_TIME) {
                    Customer customer = cashier.getCurrentCustomer();
                    cashier.endService(currentTime);
                    if (customer.getOrder() != null) {
                        customer.getOrder().setState(OrderState.PREPARING);
                        customer.setState(CustomerState.WAITING_FOR_FOOD);
                    }
                }
            }
        }
    }

    private void assignOrdersToStations() {
        for (Order order : model.getOrders()) {
            if (order.getState() == OrderState.PREPARING && !order.getItemsSent()) {
                for (MenuItem item : order.getMenuItems()) {
                    FoodStation station = model.findStation(item.getStationType());
                    KitchenTask task = new KitchenTask(order, item);
                    station.enqueue(task);
                }
                order.setItemsSent(true);
            }
        }
    }

    private void completeStationTasks() {
        for (FoodStation station : model.getFoodStations()) {
            if (station.isBusy() && station.getCurrentTask() != null) {
                KitchenTask task = station.getCurrentTask();
                MenuItem item = task.getItem();
                if (item != null && currentTime - station.getBusyStartTime() >= item.getPrepTime()) {
                    KitchenTask finished = station.endTask(currentTime);
                    Order order = finished.getOrder();
                    order.markItemPrepared();
                    if (order.isComplete()) {
                        order.setState(OrderState.READY);
                        Customer c = order.getCustomer();
                        c.setState(CustomerState.SERVED);
                        c.setDepartureTime(currentTime);
                        totalServed++;
                        totalResponseTime += c.getDepartureTime() - c.getArrivalTime();
                    }
                }
            }
        }
    }

    public void refreshView() {
        view.render(model);
    }

    public RestaurantModel getModel() {
        return model;
    }

    private void startStationTasks() {
        for (FoodStation station : model.getFoodStations()) {
            if (!station.isBusy() && station.hasQueuedTasks()) {
                KitchenTask task = station.dequeue();
                station.startTask(task, currentTime);
            }
        }
    }

    @FXML
    public void onStartSimulation() {
        initialize(2);
    }
}
