package model;
import java.util.ArrayList;
public class Order {
    private Customer customer;
    private ArrayList<MenuItem> MenuItems;
    private MenuItem orderedItem;
    private int preparedCount;
    private OrderState state;
    
    public Order(Customer customer) {
        this.customer = customer;
        MenuItems = new ArrayList<>();
        this.preparedCount = 0;
        this.state = OrderState.PLACED;
    }
    public void addItem(MenuItem item) {
        MenuItems.add(item);
    }
    public void completedItemCount() {
        preparedCount++;
    }
    public void generateRandomOrder(int amountOfItems) {
        int itemId = (int)((Math.random() * amountOfItems) + 1);
        for (MenuItem item: MenuItems) {
            if (itemId == item.getItemID()) {
                this.orderedItem = item;
            }
        }
    }
}

// TO DO: id, customer, menuItems, completed