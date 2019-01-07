package oop.assignment.restaurant.objects;

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
