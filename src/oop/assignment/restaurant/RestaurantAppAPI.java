package oop.assignment.restaurant;

import oop.assignment.restaurant.exceptions.IncompatibleOrderTypeException;
import oop.assignment.restaurant.objects.*;
import oop.assignment.restaurant.observables.OrderList;
import oop.assignment.restaurant.observables.RestaurantMap;
import oop.assignment.restaurant.observers.OrderListObserver;
import oop.assignment.restaurant.observers.RestaurantMapObserver;

public class RestaurantAppAPI {
    private RestaurantMap restaurantMap;
    private RestaurantMapObserver restaurantMapObserver;
    private OrderList<RestaurantOrder> orderList;
    private OrderListObserver orderListObserver;

    private static final RestaurantAppAPI SINGLETON = new RestaurantAppAPI();

    private RestaurantAppAPI(){
        restaurantMap = new RestaurantMap();
        restaurantMapObserver = new RestaurantMapObserver(restaurantMap);
        restaurantMap.register(restaurantMapObserver);

        orderList = null;
        orderListObserver = null;
    }

    public static RestaurantAppAPI getInstance(){
        return SINGLETON;
    }

    public Restaurant addRestaurant(String name, String orderTypesOfferedText){
        Restaurant restaurant = new Restaurant(name, OrderType.convertString(orderTypesOfferedText));
        restaurantMap.addRestaurant(restaurant);
        return restaurant;
    }

    public void addMenuItem(String restaurantName, String menuItemName, double price){
        restaurantMap.getRestaurant(restaurantName).addMenuItem(new MenuItem(menuItemName, price));
    }

    public void startNewOrderList(){
        orderList = new OrderList<>();
        orderListObserver = new OrderListObserver(orderList);
        orderList.register(orderListObserver);
    }

    public RestaurantOrder startNewOrder(String restaurantName, String orderTypeText){
        Restaurant restaurant = getRestaurant(restaurantName);
        OrderType orderType = OrderType.convertString(orderTypeText);

        if(OrderType.areCompatible(restaurant.getOrderTypesOffered(), orderType)) {
            RestaurantOrder restaurantOrder = new RestaurantOrder(orderType, restaurant);
            orderList.addOrder(restaurantOrder);
            return restaurantOrder;
        } else {
            throw new IncompatibleOrderTypeException(restaurantName, orderType);
        }
    }

    public double closeOrderList(){
        for(int i = 0; i < orderList.size(); i ++){
            addOrder(orderList.getOrder(i));
        }

        double total = orderListObserver.getTotal();
        orderList = null;
        orderListObserver = null;
        return total;
    }

    public double getTotal(){
        return restaurantMapObserver.getTotal();
    }

    public boolean isOrderListNull(){
        return orderList == null;
    }

    public boolean isOrderListEmpty(){
        return orderList == null || orderList.size() == 0;
    }

    private void addOrder(RestaurantOrder restaurantOrder){
        restaurantMap.addOrder(restaurantOrder.getRestaurantName(), restaurantOrder);
    }

    public Restaurant getRestaurant(String restaurantName){
        return restaurantMap.getRestaurant(restaurantName);
    }

    public Restaurant getHighestRevenueRestaurant(){
        return restaurantMapObserver.getHighestRevenueRestaurant();
    }

}
