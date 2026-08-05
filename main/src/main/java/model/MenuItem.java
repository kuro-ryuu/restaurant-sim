package model;

public class MenuItem {
    private int itemID;
    private int prepTime;
    private StationType stationType;
    public MenuItem(String itemName, int prepTime, StationType stationType) {
        this.itemID = itemID;
        this.prepTime = prepTime;
        this.stationType = stationType;
    }
    public int getItemID() {
        return itemID;
    }

    public int getPrepTime() {
        return prepTime;
    }
}

// TO DO: name, preparationTime
