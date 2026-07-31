package model;

public class MenuItem {
    private String itemName;
    private int prepTime;

    public String getItemName() {
        return itemName;
    }

    public String getPrepTime() {
        return Integer.toString(prepTime);
    }

    private MenuItem(String itemName, int prepTime) {
        this.itemName = itemName;
        this.prepTime = prepTime;
    }
}

// TO DO: name, preparationTime
