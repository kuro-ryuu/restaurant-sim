package app;

import controller.RestaurantCtrl;
import model.Customer;
import model.Order;
import model.RestaurantModel;

import view.RestaurantView;

public class RestaurantApp {
    public static void main(String[] args) {
        RestaurantView view = new RestaurantView();
        RestaurantModel model = new RestaurantModel();
        RestaurantCtrl controller = new RestaurantCtrl(model, view);
        controller.initialize(2);

        do {
            Customer customer = controller.createCustomer(2, 7);
            controller.placeOrder(customer);
            controller.queueCustomer(customer);
            controller.advanceTime(3);
            if (controller.getCurrentTime() > 50) {
                controller.advanceTime(30);
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("The thread was interrupted while sleeping!");
            }
        } while (true);
    }
}
