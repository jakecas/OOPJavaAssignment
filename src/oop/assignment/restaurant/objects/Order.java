package oop.assignment.restaurant.objects;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Restaurant restaurant;
    private List<MenuItem> itemList;

    public Order(Restaurant restaurant){
        this.restaurant = restaurant;
        this.itemList = new ArrayList<>();
    }

    public void orderItems(List<String> itemNames){
        for(String itemName: itemNames) {
            itemList.add(restaurant.orderItem(itemName));
        }
    }

    public double calculatePrice(){
        double total = 0;
        for(MenuItem item: itemList){
            total += item.getPrice();
        }
        return total;
    }
}
