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

        Customer customer1 = controller.createCustomer(3, 10);
        controller.placeOrder(customer1);
        controller.queueCustomer(customer1);

        Customer customer2 = controller.createCustomer(2, 7);
        controller.placeOrder(customer2);
        controller.queueCustomer(customer2);

        view.render(controller.getModel());
        
        view.render(controller.getModel());
        controller.advanceTime(50);
        
    }
}
