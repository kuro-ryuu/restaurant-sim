package model;

public class KitchenTask {
    private Order order;
    private Menu menu;
    public KitchenTask(Order order, Menu menu) {
        this.order = order;
        this.menu = menu;
    }
    public Order order() {
        return order;
    }
    public Menu menu() {
        return menu;
    }
}
