package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.objects.Order;
import oop.assignment.restaurant.objects.OrderType;

public class RestaurantOrder extends Order {
    private Restaurant restaurant;

    public RestaurantOrder(OrderType orderType, Restaurant restaurant) {
        super(orderType);
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant(){
        return restaurant;
    }

    public String getRestaurantName() {
        return restaurant.getName();
    }

}
