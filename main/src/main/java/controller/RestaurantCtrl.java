package controller;

import javafx.fxml.FXML;
import model.Cashier;
import model.Customer;
import model.CustomerState;
import model.FoodStation;
import model.KitchenTask;
import model.MenuItem;
import model.Order;
import model.OrderState;
import model.RestaurantModel;
import view.RestaurantView;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RestaurantCtrl {
    private final RestaurantModel model;
    private final RestaurantView view;
    private long currentTime;
    private static final long CASHIER_SERVICE_TIME = 5;

    public RestaurantCtrl() {
        this(new RestaurantModel(), new RestaurantView());
    }

    public RestaurantCtrl(RestaurantModel model, RestaurantView view) {
        this.model = model;
        this.view = view;
        this.currentTime = 0;
    }

    public void initialize(int cashierCount, int stationCount) {
        model.initialize(cashierCount, stationCount);
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
        List<MenuItem> items = model.getMenu().getRandomItemCount();
        items.forEach(order::addItem);
        if (!items.isEmpty()) {
            order.setOrderedItem(items.get(0));
        }
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
    }

    private void startCashierService() {
        for (Cashier cashier : model.getOrderingCounters()) {
            if (!cashier.isBusy() && !cashier.getQueue().isEmpty()) {
                Customer customer = cashier.dequeue();
                if (customer != null) {
                    cashier.startService(customer, currentTime);
                    if (customer.getOrder() != null) {
                        customer.getOrder().setState(OrderState.PREPARING);
                        customer.setState(CustomerState.WAITING_FOR_FOOD);
                    }
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
            if (order.getState() == OrderState.PREPARING && order.getOrderedItem() != null && !isOrderInProgress(order)) {
                Optional<FoodStation> station = model.findAvailableFoodStation();
                station.ifPresent(available -> available.startTask(new KitchenTask(order, model.getMenu()), currentTime));
            }
        }
    }

    private boolean isOrderInProgress(Order order) {
        return model.getFoodStations().stream()
            .anyMatch(station -> station.isBusy() && station.getCurrentTask() != null && station.getCurrentTask().order() == order);
    }

    private void completeStationTasks() {
        for (FoodStation station : model.getFoodStations()) {
            if (station.isBusy() && station.getCurrentTask() != null) {
                KitchenTask task = station.getCurrentTask();
                MenuItem item = task.order().getOrderedItem();
                if (item != null && currentTime - station.getBusyStartTime() >= item.getPrepTime()) {
                    station.EndTask(currentTime);
                    Order order = task.order();
                    order.setState(OrderState.READY);
                    Customer customer = order.getCustomer();
                    if (customer != null) {
                        customer.setState(CustomerState.SERVED);
                        customer.setDepartureTime(currentTime);
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

    @FXML
    public void onStartSimulation() {
        initialize(2, 3);
    }
}
