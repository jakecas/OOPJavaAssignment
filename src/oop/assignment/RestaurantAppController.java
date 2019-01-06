package oop.assignment;

import oop.assignment.restaurant.RestaurantAppAPI;
import oop.assignment.restaurant.objects.Restaurant;
import oop.assignment.restaurant.objects.RestaurantOrder;

import java.util.List;

public class RestaurantAppController {
    private static final RestaurantAppController SINGLETON = new RestaurantAppController();

    private Restaurant restaurant;
    private RestaurantOrder restaurantOrder;
    private RestaurantAppAPI restaurantAppAPI;

    private RestaurantAppController(){
        this.restaurant = null;
        this.restaurantOrder = null;
        restaurantAppAPI = RestaurantAppAPI.getInstance();
    }

    public static RestaurantAppController getInstance(){
        return SINGLETON;
    }


    public void executeLine(List<String> args){
        switch (args.get(0)){
            case "BeginRestaurant":
                restaurant = restaurantAppAPI.addRestaurant(args.get(1), args.get(2));
                break;
            case "Item":
                restaurantAppAPI.addMenuItem(restaurant.getName(), args.get(1), Double.parseDouble(args.get(2)));
                break;
            case "EndRestaurant":
                restaurant = null;
                break;
            case "BeginOrderList":
                restaurantAppAPI.startNewOrderList();
                break;
            case "BeginOrder":
                restaurantOrder = restaurantAppAPI.startNewOrder(args.get(1), args.get(2));
                break;
            case "OrderItem":
                restaurantOrder.addItem(getCurrentOrderRestaurant().getMenuItem(args.get(1)));
                break;
            case "EndOrder":
                restaurantOrder = null;
                break;
            case "EndOrderList":
                restaurantAppAPI.closeOrderList();
                break;
        }
    }

    public Restaurant getCurrentOrderRestaurant(){
        return restaurantAppAPI.getRestaurant(restaurantOrder.getRestaurantName());
    }
}
