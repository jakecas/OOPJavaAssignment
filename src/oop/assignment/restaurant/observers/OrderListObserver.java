package oop.assignment.restaurant.observers;

import oop.assignment.restaurant.observables.OrderList;

/**
 * The observer of the orderlist, used to keep and calculate the total price.
 */
public class OrderListObserver implements IObserver {
    /**
     * a reference to the subject of this observer, kept for ease of access to its state.
     */
    private OrderList orderList;
    /**
     * the total price of the orderlist
     */
    private double total;

    /**
     * Initialises the attributes and sets the reference to the subject
     * and registers this observer to that orderlist.
     *
     * @param orderList a reference to the subject of this observer, kept for ease of access to its state.
     */
    public OrderListObserver(OrderList orderList){
        this.orderList = orderList;
        total = 0;

        orderList.register(this);
    }

    /**
     * Loops over the list of orders and keeps a running tally of the price of each order before returning the total.
     *
     * @return the total price of the orderlist
     */
    private double calculateTotal(){
        double total = 0;
        for(int i = 0; i < orderList.size(); i++){
            total += orderList.getOrder(i).calculatePrice();
        }
        return total;
    }

    /**
     * @return the total price of the orderlist
     */
    public double getTotal() {
        return total;
    }

    /**
     * Calculates and sets the total price.
     */
    @Override
    public void update() {
        this.total = calculateTotal();
    }
}
