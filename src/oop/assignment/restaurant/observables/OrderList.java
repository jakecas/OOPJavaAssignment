package oop.assignment.restaurant.observables;

import oop.assignment.linkedlist.LinkList;
import oop.assignment.restaurant.exceptions.InvalidStateException;
import oop.assignment.restaurant.objects.MenuItem;
import oop.assignment.restaurant.observers.IObserver;
import oop.assignment.restaurant.objects.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a list of T, which must extend orders.
 * It is an observable, and thus also defines a list of observers and implements the methods.
 *
 * If the contents of the orderList are manipulated outside this class,
 * one must remember to notify the observers to maintain the relationship.
 *
 * @param <T> the type of order that the list will contain
 */
public class OrderList<T extends Order> implements IObservable {
    /**
     * the LinkList of orders.
     */
    private LinkList<T> orderList;
    /**
     * the list of observers of this orderList
     */
    private List<IObserver> observers;
    /**
     * whether the current order is open to be edited or not
     */
    private boolean orderOpen;

    /**
     * Initialises the lists and sets the order to be closed, as there are none.
     */
    public OrderList(){
        orderList = new LinkList<>();
        observers = new ArrayList<>();
        orderOpen = false;
    }

    /**
     * Appends an order to the order list and sets the order to open, then it notifies the observers.
     *
     * @param order the new order
     */
    public void addOrder(T order){
        if(orderOpen){
            throw new InvalidStateException("An order is open", "creating a new order");
        }
        orderList.append(order);
        orderOpen = true;
        notifyObservers();
    }

    /**
     * @param i the index of the order required
     * @return the order at the index
     */
    public T getOrder(int i){
        return orderList.get(i);
    }

    /**
     * @return whether the current order is open to be edited or not
     */
    public boolean isOrderOpen() {
        return orderOpen;
    }


    /**
     * Checks if the order is open before adding an item to the last order in the list and notifying the observers,
     * otherwise it throws an InvalidStateException.
     * @param menuItem the item to add to the order
     */
    public void addItemToCurrentOrder(MenuItem menuItem){
        if(!orderOpen){
            throw new InvalidStateException("No order is open", "adding an item to the order");
        }
        orderList.get(size()-1).addItem(menuItem);
        notifyObservers();
    }

    /**
     * Checks if the order is open before setting orderOpen to false, otherwise it throws an InvalidStateException.
     */
    public void closeOrder(){
        if(!orderOpen){
            throw new InvalidStateException("No order is open", "closing the order");
        }

        orderOpen = false;
    }

    /**
     * @return the size of the list of orders
     */
    public int size(){
        return orderList.size();
    }


    /**
     * Adds the passed Observer to the list of active observers of this object.
     *
     * @param observer the observer to add to the list
     */
    @Override
    public void register(IObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes the passed Observer from the list of active observers of this object.
     *
     * @param observer the observer to remove from the list
     */
    @Override
    public void unregister(IObserver observer) {
        observers.remove(observer);
    }

    /**
     * Iterates over the list of observers, calling the update method on each one.
     */
    @Override
    public void notifyObservers() {
        for(IObserver observer: observers){
            observer.update();
        }
    }
}
