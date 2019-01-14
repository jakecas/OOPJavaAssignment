package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.exceptions.IncompatibleOrderTypeException;
import oop.assignment.restaurant.exceptions.NonExistentMenuItemException;
import oop.assignment.restaurant.observables.OrderList;
import oop.assignment.restaurant.observers.OrderListObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * A restaurant is the main object of this system.
 * It has MenuItems which can be ordered, and an OrderList.
 * It also keeps an observer of the OrderList, so as to calculate its revenue.
 */
public class Restaurant {
    /**
     * the name of this restaurant
     */
    private String name;
    /**
     * the types of order offered by this restaurant
     */
    private OrderType orderTypesOffered;
    /**
     * the map containing the MenuItem names and the MenuItems themselves for easy access by name
     */
    private Map<String, MenuItem> menuItemMap;
    /**
     * the list of orders made through this restaurant
     */
    private OrderList<Order> orderList;
    /**
     * the observer over the orderList, it is what keeps the revenue of this restaurant updated.
     */
    private OrderListObserver orderListObserver;

    /**
     * Initialises the attributes of this restaurant, als registering the orderListObserver.
     *
     * @param name the name of this restaurant
     * @param orderTypesOffered the types of order offered by this restaurant
     */
    public Restaurant(String name, OrderType orderTypesOffered) {
        this.name = name;
        this.orderTypesOffered = orderTypesOffered;
        this.menuItemMap = new HashMap<>();
        this.orderList = new OrderList<>();
        this.orderListObserver = new OrderListObserver(orderList);
    }

    /**
     * @return the name of this restaurant
     */
    public String getName() {
        return name;
    }

    /**
     * @return the types of order offered by this restaurant
     */
    public OrderType getOrderTypesOffered(){
        return orderTypesOffered;
    }

    /**
     * Puts the MenuItem in the menu of this restaurant
     *
     * @param menuItem the new MenuItem
     */
    public void addMenuItem(MenuItem menuItem){
        menuItemMap.put(menuItem.getName(), menuItem);
    }

    /**
     * Returns the MenuItem if it exists, and throws a NonExistentMenuItemException otherwise.
     *
     * @param itemName the name of the required MenuItem
     * @return the MenuItem required
     */
    public MenuItem getMenuItem(String itemName){
        MenuItem tmpMenuItem = menuItemMap.get(itemName);

        if(tmpMenuItem == null) {
            throw new NonExistentMenuItemException(itemName);
        } else {
            return tmpMenuItem;
        }
    }

    /**
     * Returns the revenue of this restaurant through the orderListObserver.
     *
     * @return the revenue of this restaurant
     */
    public double getRevenue() {
        return orderListObserver.getTotal();
    }

    /**
     * If the Order is compatible with this restaurant, it adds it to the OrderList and closes it immediately,
     * otherwise it throws an IncompatibleOrderTypeException.
     *
     * @param order the order to add to the OrderList
     */
    public void addOrder(Order order){
        if(OrderType.areCompatible(this.getOrderTypesOffered(), order.getOrderType())) {
            orderList.addOrder(order);
            orderList.closeOrder();
        } else {
            throw new IncompatibleOrderTypeException(this.getName(), order.getOrderType());
        }
    }
}
