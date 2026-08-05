package view;

import model.Cashier;
import model.Customer;
import model.FoodStation;
import model.Order;
import model.RestaurantModel;

public class RestaurantView {
    public void render(RestaurantModel model) {
        System.out.println("\n--- Restaurant Simulation State ---");
        System.out.println("Cashiers:");
        for (Cashier cashier : model.getOrderingCounters()) {
            System.out.printf("  Cashier %d: busy=%s, queue=%d, current=%s\n",
                    cashier.getId(), cashier.isBusy(), cashier.getQueue().size(),
                    cashier.getCurrentCustomer() == null ? "none" : cashier.getCurrentCustomer());
        }

        System.out.println("Food stations:");
        for (FoodStation station : model.getFoodStations()) {
            System.out.printf("  Station %d: busy=%s, queue=%d, current=%s\n",
                    station.getId(), station.isBusy(), station.getQueue().size(),
                    station.getCurrentTask() == null ? "none" : station.getCurrentTask().order());
        }

        System.out.println("Customers and orders:");
        for (Customer customer : model.getCustomers()) {
            Order order = customer.getOrder();
            String orderDesc = order == null ? "none" : order.getState() + " item=" +
                    (order.getOrderedItem() == null ? "none" : order.getOrderedItem().getName());
            System.out.printf("  %s: state=%s, arrival=%d, departure=%d, order=[%s]\n",
                    customer, customer.getState(), customer.getArrivalTime(),
                    customer.getDepartureTime(), orderDesc);
        }
        System.out.println("-----------------------------------\n");
    }
}
