package oop.assignment.restaurant.observables;

import oop.assignment.restaurant.objects.Order;
import oop.assignment.restaurant.objects.Restaurant;
import oop.assignment.restaurant.observers.IObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantMap implements IObservable {
    private Map<String, Restaurant> restaurantMap;
    private List<IObserver> observers;

    public RestaurantMap(){
        restaurantMap = new HashMap<>();
        observers = new ArrayList<>();
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantMap.put(restaurant.getName(), restaurant);
    }

    public Restaurant getRestaurant(String name){
        return restaurantMap.get(name);
    }

    public void addOrder(String restaurantName, Order order){
        restaurantMap.get(restaurantName).addOrder(order);
        notifyObservers();
    }

    public Map<String, Restaurant> getRestaurantMap() {
        return restaurantMap;
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
