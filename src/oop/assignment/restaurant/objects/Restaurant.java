package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.exceptions.NonExistentMenuItemException;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private boolean delivery;
    private boolean takeaway;
    private List<MenuItem> menuItemList;
    private double revenue;

    public Restaurant(String name, boolean delivery, boolean takeaway, double revenue) {
        this.name = name;
        this.delivery = delivery;
        this.takeaway = takeaway;
        this.menuItemList = new ArrayList<>();
        this.revenue = revenue;
    }

    public MenuItem orderItem(String itemName){
        for(MenuItem item: menuItemList){
            if(item.getName().equals(itemName)){
                revenue += item.getPrice();
                return item;
            }
        }
        throw new NonExistentMenuItemException(itemName);
    }

    public double getRevenue() {
        return revenue;
    }
}
