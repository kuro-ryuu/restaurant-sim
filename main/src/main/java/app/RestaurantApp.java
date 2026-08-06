package app;

import controller.RestaurantCtrl;
import model.RestaurantModel;
import view.RestaurantView;

public class RestaurantApp {
    public static void main(String[] args) {
        RestaurantView view = new RestaurantView();
        RestaurantModel model = new RestaurantModel();
        RestaurantCtrl controller = new RestaurantCtrl(model, view);
        controller.initialize(2);
        controller.setArrivalRange(2, 7);

        while (controller.getCurrentTime() <= 50) {
            controller.advanceTime(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("The thread was interrupted while sleeping!");
            }
        }
    }
}
