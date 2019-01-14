package oop.assignment.restaurant.observables;

import oop.assignment.restaurant.exceptions.NonExistentRestaurantException;
import oop.assignment.restaurant.objects.Order;
import oop.assignment.restaurant.objects.Restaurant;
import oop.assignment.restaurant.observers.IObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a Map of restaurants, created so as to define observers on it
 * to monitor the total spent and the highest earning restaurant.
 * It is an observable, and thus defines the list of observers and implements the methods.
 *
 * If the contents of the restaurantMap are manipulated outside this class,
 * one must remember to notify the observers to maintain the relationship.
 */
public class RestaurantMap implements IObservable {
    /**
     * the map of restaurant names with restaurants, for ease of access by name
     */
    private Map<String, Restaurant> restaurantMap;
    /**
     * the list of observers of this orderList
     */
    private List<IObserver> observers;

    /**
     * Initialises the lists and sets the order to be closed, as there are none.
     */
    public RestaurantMap(){
        restaurantMap = new HashMap<>();
        observers = new ArrayList<>();
    }

    /**
     * Puts the Restaurant in the map of restaurants.
     *
     * @param restaurant the restaurant to add to the map
     */
    public void addRestaurant(Restaurant restaurant){
        restaurantMap.put(restaurant.getName(), restaurant);
    }

    /**
     * Checks the list for the restaurant with the passed name,
     * returning it if found and throwing a NonExistentRestaurantException if not.
     *
     * @param name the name of the restaurant required
     * @return the required restaurant
     */
    public Restaurant getRestaurant(String name){
        Restaurant restaurant = restaurantMap.get(name);
        if(restaurant == null){
            throw new NonExistentRestaurantException(name);
        }
        return restaurant;
    }

    /**
     * Adds the order to the appropriate restaurant, then notifies the observers.
     *
     * @param restaurantName the name of the restaurant to which to add the order to
     * @param order the order to add to the restaurant
     */
    public void addOrder(String restaurantName, Order order){
        getRestaurant(restaurantName).addOrder(order);
        notifyObservers();
    }

    /**
     * @return the map of restaurant names with restaurants, for ease of access by name
     */
    public Map<String, Restaurant> getRestaurantMap() {
        return restaurantMap;
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
