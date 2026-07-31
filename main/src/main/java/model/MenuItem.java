package model;

public class MenuItem {
    private static int itemID;
    private int prepTime;

    public static int getItemID() {
        return itemID;
    }

    public int getPrepTime() {
        return prepTime;
    }

    private MenuItem(String itemName, int prepTime) {
        this.itemID = itemID;
        this.prepTime = prepTime;
    }
}

// TO DO: name, preparationTime
