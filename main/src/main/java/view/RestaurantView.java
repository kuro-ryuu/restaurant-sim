package view;


import model.*;
import controller.*;
public class RestaurantView {
   
    public void render(RestaurantModel model, long currentTime) {
        System.out.println("\n--- Restaurant Simulation State ---");
        System.out.printf("\n--- Clock: %d ---", currentTime);
        System.out.println("\nCashiers:");
        for (Cashier cashier : model.getOrderingCounters()) {
            System.out.printf("  Cashier %d: busy=%s, queue=%d, current=%s\n",
                    cashier.getId(), cashier.isBusy(), cashier.getQueue().size(),
                    cashier.getCurrentCustomer() == null ? "none" : cashier.getCurrentCustomer());
        }

        System.out.println("Food stations:");
        for (FoodStation station : model.getFoodStations()) {
            System.out.printf("  Station %s: busy=%s, queue=%d, current=%s\n",
                    station.getStationType(), station.isBusy(), station.getQueue().size(),
                    station.getCurrentTask() == null ? "none" : station.getCurrentTask().getItem().getName());
        }

        System.out.println("Customers and orders:");
        for (Customer customer : model.getCustomers()) {
            Order order = customer.getOrder();
            String orderDesc;
            if (order == null) {
                orderDesc = "none";
            } 
            else {
                String items = "";
                for (MenuItem item : order.getMenuItems()) {
                    items += item.getName() + " ";
                }
                orderDesc = order.getState() + " [" + items + "] " + order.getPreparedCount() + "/" + order.getMenuItems().size();
            }
            System.out.printf("  %s: state=%s, arrival=%d, departure=%d, order=[%s]\n",
                    customer, customer.getState(), customer.getArrivalTime(),
                    customer.getDepartureTime(), orderDesc);
        }
        System.out.println("-----------------------------------\n");
    }

}
