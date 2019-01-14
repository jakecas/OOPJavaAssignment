package oop.assignment.restaurant.observers;

import oop.assignment.restaurant.objects.OrderType;
import oop.assignment.restaurant.objects.Restaurant;
import oop.assignment.restaurant.observables.RestaurantMap;

/**
 * The observer of the restaurantmap, used to keep and calculate the total spent across all restaurants
 * and to find the restaurant with the highest revenue.
 */
public class RestaurantMapObserver implements IObserver {
    /**
     * a reference to the subject of this observer, kept for ease of access to its state.
     */
    private RestaurantMap restaurantMap;
    /**
     * the restaurant with the highest revenue
     */
    private Restaurant highestRevenueRestaurant;
    /**
     * the total spent across all restaurants
     */
    private double total;

    /**
     * Initialises the attributes and sets the reference to the subject
     * and registers this observer to that orderlist.
     *
     * @param restaurantMap a reference to the subject of this observer, kept for ease of access to its state.
     */
    public RestaurantMapObserver(RestaurantMap restaurantMap) {
        this.restaurantMap = restaurantMap;
        this.highestRevenueRestaurant = null;
        this.total = 0;

        restaurantMap.register(this);
    }

    /**
     * @return the restaurant with the highest revenue
     */
    public Restaurant getHighestRevenueRestaurant() {
        return highestRevenueRestaurant;
    }

    /**
     * @return the total spent across all restaurants
     */
    public double getTotal() {
        return total;
    }

    /**
     * Loops over the map of restaurant, keeping track of the restaurant with the highest revenue.
     * When it reaches the end of the list, it returns the restaurant it was tracking.
     * This might cause it to return a dummy object, in the case where no restaurant had any revenue.
     *
     * @return the restaurant with the highest revenue
     */
    private Restaurant findHighestRevenueRestaurant(){
        Restaurant tempRestaurant = new Restaurant("dummy", OrderType.BOTH);
        for(Restaurant restaurant : restaurantMap.getRestaurantMap().values()){
            if(restaurant.getRevenue() > tempRestaurant.getRevenue()){
                tempRestaurant = restaurant;
            }
        }
        return tempRestaurant;
    }

    /**
     * Iterates through the restaurants and keeps a running tally
     * of the revenue of each restaurantbefore returning the total.
     *
     * @return the total spent across all restaurants
     */
    private double calculateTotal(){
        double total = 0;

        for(Restaurant restaurant: restaurantMap.getRestaurantMap().values()){
            total += restaurant.getRevenue();
        }

        return total;
    }

    /**
     * Finds and sets the restaurant with the highest revenue, as well as the total spent across all restaurants.
     */
    @Override
    public void update() {
        this.highestRevenueRestaurant = findHighestRevenueRestaurant();
        this.total = calculateTotal();
    }


}
