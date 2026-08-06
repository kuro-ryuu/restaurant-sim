package app;

import controller.RestaurantCtrl;
import model.RestaurantModel;
import view.RestaurantView;

public class RestaurantApp {
    private static int simDuration = 50;
    private static int speedMs = 1000;
    private static int simulationSpeed = 13;
    public static void main(String[] args) {
        RestaurantView view = new RestaurantView();
        RestaurantModel model = new RestaurantModel();
        RestaurantCtrl controller = new RestaurantCtrl(model, view);
        controller.initialize(2);
        controller.setArrivalRange(2, 7);

        while (controller.getCurrentTime() < simDuration) {
            long remaining = simDuration - controller.getCurrentTime();
            long step = Math.min(simulationSpeed, remaining);
            controller.advanceTime(step);
            try {
                Thread.sleep(speedMs);
            } catch (InterruptedException e) {
                System.out.println("The thread was interrupted while sleeping!");
            }
        }
    }
}
