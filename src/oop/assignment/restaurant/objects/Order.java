package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.exceptions.InvalidOrderTypeException;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of MenuItems along with the OrderType.
 */
public class Order {
    /**
     * the type of order that this is
     */
    private OrderType orderType;
    /**
     * the list of items in this order
     */
    private List<MenuItem> itemList;

    /**
     * Checks that the orderType is valid (i.e. not BOTH, in which case it throws an InvalidOrderTypeException),
     * and initialises the list of items.
     *
     * @param orderType the type of order that this is
     */
    public Order(OrderType orderType){
        if(orderType == OrderType.BOTH){
            throw new InvalidOrderTypeException(orderType);
        }
        this.orderType = orderType;
        this.itemList = new ArrayList<>();
    }

    /**
     * @return the type of order that this is
     */
    public OrderType getOrderType(){
        return orderType;
    }

    /**
     * Adds the new MenuItem to the list of items in this order.
     *
     * @param menuItem the item to add to this order
     */
    public void addItem(MenuItem menuItem){
        itemList.add(menuItem);
    }


    /**
     * Iterates over the list of items and adds the prices of each before returning the total.
     *
     * @return the total price of this order
     */
    public double calculatePrice(){
        double total = 0;
        for(MenuItem item: itemList){
            total += item.getPrice();
        }
        return total;
    }
}
