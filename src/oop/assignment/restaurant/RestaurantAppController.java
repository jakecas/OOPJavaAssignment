package oop.assignment.restaurant;

import oop.assignment.restaurant.exceptions.EmptyOrderListException;
import oop.assignment.restaurant.exceptions.IncompatibleOrderTypeException;
import oop.assignment.restaurant.exceptions.InvalidStateException;
import oop.assignment.restaurant.objects.*;
import oop.assignment.restaurant.observables.OrderList;
import oop.assignment.restaurant.observables.RestaurantMap;
import oop.assignment.restaurant.observers.OrderListObserver;
import oop.assignment.restaurant.observers.RestaurantMapObserver;

public class RestaurantAppController {
    private RestaurantMap restaurantMap;
    private RestaurantMapObserver restaurantMapObserver;
    private OrderList<RestaurantOrder> orderList;
    private Restaurant restaurant;
    private OrderListObserver orderListObserver;

    private static final RestaurantAppController SINGLETON = new RestaurantAppController();

    private RestaurantAppController(){
        restaurantMap = new RestaurantMap();
        restaurantMapObserver = new RestaurantMapObserver(restaurantMap);

        orderList = null;
        orderListObserver = null;
    }

    public static RestaurantAppController getInstance(){
        return SINGLETON;
    }

    public void addRestaurant(String name, String orderTypesOfferedText){
        if(restaurant != null){
            throw new InvalidStateException("A restaurant session is open", "creating a restaurant");
        }
        restaurant = new Restaurant(name, OrderType.convertString(orderTypesOfferedText));
        restaurantMap.addRestaurant(restaurant);
    }

    public void addMenuItem(String menuItemName, double price){
        if(restaurant == null){
            throw new InvalidStateException("Restaurant is null", "adding an item to the restaurant");
        }
        restaurant.addMenuItem(new MenuItem(menuItemName, price));
    }

    public void closeCurrentRestaurant(){
        if(restaurant == null){
            throw new InvalidStateException("Restaurant is null", "closing the restaurant");
        }
        restaurant = null;
    }

    public void startNewOrderList(){
        if(orderList != null){
            throw new InvalidStateException("An order list exists", "creating an order list");
        }

        orderList = new OrderList<>();
        orderListObserver = new OrderListObserver(orderList);
    }

    public void startNewOrder(String restaurantName, String orderTypeText){
        if(orderList == null){
            throw new InvalidStateException("Order list is null", "starting a new order");
        }

        Restaurant restaurant = getRestaurant(restaurantName);
        OrderType orderType = OrderType.convertString(orderTypeText);

        if(OrderType.areCompatible(restaurant.getOrderTypesOffered(), orderType)) {
            RestaurantOrder restaurantOrder = new RestaurantOrder(orderType, restaurant);
            orderList.addOrder(restaurantOrder);
        } else {
            throw new IncompatibleOrderTypeException(restaurantName, orderType);
        }
    }

    public void addItemToCurrentOrder(String itemName){
        if(isOrderListEmpty()){
            throw new EmptyOrderListException("could not add item to order");
        }
        Restaurant restaurant = orderList.getOrder(orderList.size()-1).getRestaurant();
        orderList.addItemToCurrentOrder(restaurant.getMenuItem(itemName));
    }

    public void closeCurrentOrder(){
        if(orderList == null){
            throw new InvalidStateException("Order list is null", "closing an order");
        }
        orderList.closeOrder();
    }

    public double closeOrderList()  {
        if(orderList == null){
            throw new InvalidStateException("Order list is null", "closing an order list");
        }

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

    public boolean isRestaurantSessionOpen(){
        return restaurant != null;
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
        if(restaurantMapObserver == null) {
            return null;
        }
        return restaurantMapObserver.getHighestRevenueRestaurant();
    }

}
