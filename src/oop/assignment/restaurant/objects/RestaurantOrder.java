package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.objects.Order;
import oop.assignment.restaurant.objects.OrderType;

public class RestaurantOrder extends Order {
    private String restaurantName;

    public RestaurantOrder(OrderType orderType, String restaurantName) {
        super(orderType);
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

}
