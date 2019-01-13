package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.exceptions.IncompatibleOrderTypeException;
import oop.assignment.restaurant.exceptions.NonExistentMenuItemException;
import oop.assignment.restaurant.observables.OrderList;
import oop.assignment.restaurant.observers.OrderListObserver;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    private String name;
    private OrderType orderTypesOffered;
    private Map<String, MenuItem> menuItemMap;
    private OrderList<Order> orderList;
    private OrderListObserver orderListObserver;

    public Restaurant(String name, OrderType orderTypesOffered) {
        this.name = name;
        this.orderTypesOffered = orderTypesOffered;
        this.menuItemMap = new HashMap<>();
        this.orderList = new OrderList<>();
        this.orderListObserver = new OrderListObserver(orderList);
    }

    public String getName() {
        return name;
    }

    public OrderType getOrderTypesOffered(){
        return orderTypesOffered;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItemMap.put(menuItem.getName(), menuItem);
    }

    public MenuItem getMenuItem(String itemName){
        MenuItem tmpMenuItem = menuItemMap.get(itemName);

        if(tmpMenuItem == null) {
            throw new NonExistentMenuItemException(itemName);
        } else {
            return tmpMenuItem;
        }
    }

    public double getRevenue() {
        return orderListObserver.getTotal();
    }

    public void addOrder(Order order){
        if(OrderType.areCompatible(this.getOrderTypesOffered(), order.getOrderType())) {
            orderList.addOrder(order);
            orderList.closeOrder();
        } else {
            throw new IncompatibleOrderTypeException(this.getName(), order.getOrderType());
        }
    }
}
