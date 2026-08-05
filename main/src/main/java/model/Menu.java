package model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> items;
    public Menu() {
        this.items = new ArrayList<>();
        items.add(new MenuItem("Chicken", 5, StationType.CHICKEN));
        items.add(new MenuItem("Rice", 6, StationType.RICE));
        items.add(new MenuItem("Fries", 3, StationType.FRIES));
    }
    public MenuItem getRandomItem() {
        return items.get((int)(Math.random() * items.size() + 1));
    }
    public List<MenuItem> getRandomItemCount() {
        int count = (int)(Math.random() * items.size() + 1);
        List<MenuItem> selected = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            selected.add(getRandomItem());
        }
        return selected;
    }
}
