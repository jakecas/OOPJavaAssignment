package oop.assignment.restaurant.observers;

import oop.assignment.restaurant.objects.Restaurant;
import oop.assignment.restaurant.observables.RestaurantMap;

public class RestaurantMapObserver implements IObserver {
    private RestaurantMap restaurantMap;
    private Restaurant highestRevenueRestaurant;

    public RestaurantMapObserver(RestaurantMap restaurantMap) {
        this.restaurantMap = restaurantMap;
        this.highestRevenueRestaurant = null;
    }

    public Restaurant findHighestRevenueRestaurant(){
        Restaurant tempRestaurant = new Restaurant("dummy", false, false, 0);
        for(Restaurant restaurant : restaurantMap.getRestaurantMap().values()){
            if(restaurant.getRevenue() > tempRestaurant.getRevenue()){
                tempRestaurant = restaurant;
            }
        }
        return tempRestaurant;
    }

    @Override
    public void update() {
        highestRevenueRestaurant = findHighestRevenueRestaurant();
    }
}
