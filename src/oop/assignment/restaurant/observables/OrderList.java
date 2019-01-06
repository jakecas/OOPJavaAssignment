package oop.assignment.restaurant.observables;

import oop.assignment.restaurant.observers.IObserver;
import oop.assignment.restaurant.objects.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderList<T extends Order> implements IObservable {
    private List<T> orderList;
    private List<IObserver> observers;

    public OrderList(){
        orderList = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addOrder(T order){
        orderList.add(order);
        notifyObservers();
    }

    public T getOrder(int i){
        return orderList.get(i);
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
