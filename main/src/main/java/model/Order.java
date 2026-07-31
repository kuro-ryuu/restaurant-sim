package model;
import java.util.ArrayList;
public class Order {
    private Customer customer;
    private ArrayList<MenuItem> MenuItems;
    private boolean orderCompletion = false;
    private MenuItem orderedItem;
    private int preparedCount = 0;
    
    public Order(Customer customer) {
        this.customer = customer;
        MenuItems = new ArrayList<>();
    }
    public void changeState() {
        this.orderCompletion = !orderCompletion;
    }
    public boolean getOrderCompletion() {
        return orderCompletion;
    }
    public void addItem(MenuItem item) {
        MenuItems.add(item);
    }
    public void completedItemCount() {
        preparedCount++;
    }
    public void randomItem(int amountOfItems) {
        int itemId = (int)((Math.random() * amountOfItems) + 1);
        for (MenuItem item: MenuItems) {
            if (itemId == item.getItemID()) {
                this.orderedItem = item;
            }
        }
    }
}

// TO DO: id, customer, menuItems, completed