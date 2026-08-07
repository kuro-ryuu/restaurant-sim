package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final Customer customer;
    private final List<MenuItem> menuItems;
    private MenuItem orderedItem;
    private int preparedCount;
    private OrderState state;
    private boolean itemsSent;

    public Order(Customer customer) {
        this.customer = customer;
        this.menuItems = new ArrayList<>();
        this.preparedCount = 0;
        this.state = OrderState.PLACED;
        this.itemsSent = false;
    }

    public boolean getItemsSent() {
        return itemsSent;
    }

    public void setItemsSent(boolean sent) {
        this.itemsSent = sent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(menuItems);
    }

    public MenuItem getOrderedItem() {
        return orderedItem;
    }

    public int getPreparedCount() {
        return preparedCount;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void setOrderedItem(MenuItem orderedItem) {
        this.orderedItem = orderedItem;
    }

    public void addItem(MenuItem item) {
        menuItems.add(item);
    }

    public boolean isComplete() {
        return !menuItems.isEmpty() && preparedCount >= menuItems.size();
    }
    public int getRemainingCount() {
        return menuItems.size() - preparedCount;
    }
    public void markItemPrepared() {
        preparedCount++;
    }
}
