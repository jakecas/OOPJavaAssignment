package oop.assignment.restaurant.observables;

import oop.assignment.linkedlist.LinkList;
import oop.assignment.restaurant.exceptions.InvalidStateException;
import oop.assignment.restaurant.objects.MenuItem;
import oop.assignment.restaurant.observers.IObserver;
import oop.assignment.restaurant.objects.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderList<T extends Order> implements IObservable {
    private LinkList<T> orderList;
    private List<IObserver> observers;
    private boolean orderOpen;

    public OrderList(){
        orderList = new LinkList<>();
        observers = new ArrayList<>();
        orderOpen = false;
    }

    public void addOrder(T order){
        if(orderOpen){
            throw new InvalidStateException("An order is open", "creating a new order");
        }
        orderList.append(order);
        orderOpen = true;
        notifyObservers();
    }

    public T getOrder(int i){
        return orderList.get(i);
    }

    public boolean isOrderOpen() {
        return orderOpen;
    }

    public void addItemToCurrentOrder(MenuItem menuItem){
        if(!orderOpen){
            throw new InvalidStateException("No order is open", "adding an item to the order");
        }
        orderList.get(size()-1).addItem(menuItem);
        notifyObservers();
    }

    public void closeOrder(){
        if(!orderOpen){
            throw new InvalidStateException("No order is open", "closing the order");
        }

        orderOpen = false;
    }

    public int size(){
        return orderList.size();
    }


    @Override
    public void register(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer: observers){
            observer.update();
        }
    }
}
