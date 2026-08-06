package model;

public class KitchenTask {
    private Order order;
    private MenuItem item;
    public KitchenTask(Order order, MenuItem item) {
        this.order = order;
        this.item = item;
    }
    public Order getOrder() {
        return order;
    }
    public MenuItem getItem() {
        return item;
    }
}
