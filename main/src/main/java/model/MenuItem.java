package model;

public class MenuItem {
    private static int idCounter = 1;
    private final int itemID;
    private final String name;
    private final int prepTime;
    private final StationType stationType;

    public MenuItem(String name, int prepTime, StationType stationType) {
        this.itemID = idCounter++;
        this.name = name;
        this.prepTime = prepTime;
        this.stationType = stationType;
    }

    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public StationType getStationType() {
        return stationType;
    }
}
