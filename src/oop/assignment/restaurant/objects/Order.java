package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.exceptions.InvalidOrderTypeException;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private OrderType orderType;
    private List<MenuItem> itemList;

    public Order(OrderType orderType){
        if(orderType == OrderType.BOTH){
            throw new InvalidOrderTypeException(orderType);
        }
        this.orderType = orderType;
        this.itemList = new ArrayList<>();
    }

    public OrderType getOrderType(){
        return orderType;
    }

    public void addItem(MenuItem menuItem){
        itemList.add(menuItem);
    }


    public double calculatePrice(){
        double total = 0;
        for(MenuItem item: itemList){
            total += item.getPrice();
        }
        return total;
    }
}
