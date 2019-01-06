package oop.assignment.restaurant.observers;

import oop.assignment.restaurant.objects.OrderType;
import oop.assignment.restaurant.objects.Restaurant;
import oop.assignment.restaurant.observables.RestaurantMap;

public class RestaurantMapObserver implements IObserver {
    private RestaurantMap restaurantMap;
    private Restaurant highestRevenueRestaurant;
    private double total;

    public RestaurantMapObserver(RestaurantMap restaurantMap) {
        this.restaurantMap = restaurantMap;
        this.highestRevenueRestaurant = null;
        this.total = 0;
    }

    public Restaurant getHighestRevenueRestaurant() {
        return highestRevenueRestaurant;
    }

    public double getTotal() {
        return total;
    }

    private Restaurant findHighestRevenueRestaurant(){
        Restaurant tempRestaurant = new Restaurant("dummy", OrderType.DELIVERYANDTAKEAWAY);
        for(Restaurant restaurant : restaurantMap.getRestaurantMap().values()){
            if(restaurant.getRevenue() > tempRestaurant.getRevenue()){
                tempRestaurant = restaurant;
            }
        }
        return tempRestaurant;
    }

    private int calculateTotal(){
        int total = 0;

        for(Restaurant restaurant: restaurantMap.getRestaurantMap().values()){
            total += restaurant.getRevenue();
        }

        return total;
    }

    @Override
    public void update() {
        this.highestRevenueRestaurant = findHighestRevenueRestaurant();
        this.total = calculateTotal();
    }


}
