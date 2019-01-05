package oop.assignment.restaurant.observables;

import oop.assignment.linkedlist.LinkList;
import oop.assignment.restaurant.observers.IObserver;
import oop.assignment.restaurant.objects.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderList implements IObservable {
    private LinkList<Order> orderLinkList;
    private List<IObserver> observers;

    public OrderList(){
        orderLinkList = new LinkList<>();
        observers = new ArrayList<>();
    }

    public void addOrder(Order order){
        orderLinkList.append(order);
        notifyObservers();
    }

    public LinkList<Order> getOrderLinkList() {
        return orderLinkList;
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
