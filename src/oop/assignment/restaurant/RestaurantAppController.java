package oop.assignment.restaurant;

import oop.assignment.restaurant.exceptions.EmptyOrderListException;
import oop.assignment.restaurant.exceptions.IncompatibleOrderTypeException;
import oop.assignment.restaurant.exceptions.InvalidStateException;
import oop.assignment.restaurant.objects.MenuItem;
import oop.assignment.restaurant.objects.OrderType;
import oop.assignment.restaurant.objects.Restaurant;
import oop.assignment.restaurant.objects.RestaurantOrder;
import oop.assignment.restaurant.observables.OrderList;
import oop.assignment.restaurant.observables.RestaurantMap;
import oop.assignment.restaurant.observers.OrderListObserver;
import oop.assignment.restaurant.observers.RestaurantMapObserver;

/**
 * A singleton object that provides an abstraction layer from the system,
 * a program which wishes to connect to this controller would only need to get its instance,
 * and use its functions.
 *
 * The functions take only primitive datatypes,
 * constructing the required objects themselves and handling all the exceptions.
 *
 * It keeps the last created restaurant and orderlist so as to keep editing them, until they are closed.
 * Since it holds another orderlist, it also defines an observer on it as opposed to just on the restaurantMap.
 */
public class RestaurantAppController {
    /**
     * the map of all restaurants in the system
     */
    private RestaurantMap restaurantMap;
    /**
     * the observer on the restaurant map,
     * to hold the total spent across all restaurants and the one with the highest revenue
     */
    private RestaurantMapObserver restaurantMapObserver;
    /**
     * the list of orders currently being added to
     */
    private OrderList<RestaurantOrder> orderList;
    /**
     * the observer on the orderList, which holds its total price
     */
    private OrderListObserver orderListObserver;
    /**
     * the current restaurant being edited
     */
    private Restaurant restaurant;

    /**
     * the singleton instance of this class
     */
    private static final RestaurantAppController SINGLETON = new RestaurantAppController();

    /**
     * The constructor is set to be private for the purposes of the singleton design pattern.
     *
     * Initialises the restaurantMap and registers an observer,
     * and sets the orderList and its observer to null,
     * as they only exist when the user needs them.
     */
    private RestaurantAppController(){
        restaurantMap = new RestaurantMap();
        restaurantMapObserver = new RestaurantMapObserver(restaurantMap);

        orderList = null;
        orderListObserver = null;
    }

    /**
     * @return the singleton instance of this class
     */
    public static RestaurantAppController getInstance(){
        return SINGLETON;
    }

    /**
     * If there is no restaurant editing session open,
     * it creates and adds the restaurant to the map and keeps the reference to it as an open session,
     * otherwise it throws an InvalidStateException
     *
     * @param name the name of the new restaurant
     * @param orderTypesOfferedText the types of orders offered by the new restaurant
     */
    public void addRestaurant(String name, String orderTypesOfferedText){
        if(restaurant != null){
            throw new InvalidStateException("A restaurant session is open", "creating a restaurant");
        }
        restaurant = new Restaurant(name, OrderType.convertString(orderTypesOfferedText));
        restaurantMap.addRestaurant(restaurant);
    }

    /**
     * If there is a restaurant editing session open,
     * it creates and adds the MenuItem to the current restaurant,
     * otherwise it throws an InvalidStateException
     *
     * @param menuItemName the name of the new menu item
     * @param price the price of the new menu item
     */
    public void addMenuItem(String menuItemName, double price){
        if(restaurant == null){
            throw new InvalidStateException("Restaurant is null", "adding an item to the restaurant");
        }
        restaurant.addMenuItem(new MenuItem(menuItemName, price));
    }

    /**
     * Removes the reference to the last restaurant, thus closing the session.
     * If there was no session to close, it throws an InvalidStateException
     */
    public void closeCurrentRestaurant(){
        if(restaurant == null){
            throw new InvalidStateException("Restaurant is null", "closing the restaurant");
        }
        restaurant = null;
    }

    /**
     * If there already is an orderlist, it throws an InvalidStateException.
     * If there is not, it initialises the orderList and registers an observer.
     */
    public void startNewOrderList(){
        if(orderList != null){
            throw new InvalidStateException("An order list exists", "creating an order list");
        }

        orderList = new OrderList<>();
        orderListObserver = new OrderListObserver(orderList);
    }

    /**
     * It checks if an OrderList exists, in which case it gets the restaurant,
     * tries to converts the orderTypeText to an OrderType,
     * and tries to add the order to the restaurant.
     * It throws the appropriate exceptions where needed.
     *
     * @param restaurantName the name of the restaurant the new order will belong to
     * @param orderTypeText the OrderType of the new order
     */
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

    /**
     * Checks if the orderList exists and isn't empty, if not it throws an InvalidStateException.
     *
     * If all is well, it gets the last order and adds the item to it.
     *
     * @param itemName the name of the item to add to the current order
     */
    public void addItemToCurrentOrder(String itemName){
        if(isOrderListEmpty()){
            throw new EmptyOrderListException("could not add item to order");
        }
        Restaurant restaurant = orderList.getOrder(orderList.size()-1).getRestaurant();
        orderList.addItemToCurrentOrder(restaurant.getMenuItem(itemName));
    }

    /**
     * If there is no open order, it throws an InvalidStateException, otherwise it closes the order.
     */
    public void closeCurrentOrder(){
        if(orderList == null){
            throw new InvalidStateException("Order list is null", "closing an order");
        }
        orderList.closeOrder();
    }

    /**
     * If there is no open orderList, it throws an InvalidStateException.
     *
     * Otherwise, it adds each RestaurantOrder in the list to the appropriate restaurant
     * through the addOrder method below and returns the total price of the orderlist.
     * It also deletes the orderList and orderListObserver, so that new ones can be created.
     *
     * @return the total cost of the orderlist
     */
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

    /**
     * @return if there is a restaurant editing session open
     */
    public boolean isRestaurantSessionOpen(){
        return restaurant != null;
    }

    /**
     * @return if the orderList is null
     */
    public boolean isOrderListNull(){
        return orderList == null;
    }

    /**
     * @return if the orderList is null or empty
     */
    public boolean isOrderListEmpty(){
        return orderList == null || orderList.size() == 0;
    }

    /**
     * Adds the restaurant order to the appropriate restaurant.
     *
     * @param restaurantOrder the RestaurantOrder to process
     */
    private void addOrder(RestaurantOrder restaurantOrder){
        restaurantMap.addOrder(restaurantOrder.getRestaurantName(), restaurantOrder);
    }

    /**
     * @param restaurantName the name of the restaurant required
     * @return the restaurant required
     */
    public Restaurant getRestaurant(String restaurantName){
        return restaurantMap.getRestaurant(restaurantName);
    }

    /**
     * @return the restaurant with the highest revenue
     */
    public Restaurant getHighestRevenueRestaurant(){
        if(restaurantMapObserver == null) {
            return null;
        }
        return restaurantMapObserver.getHighestRevenueRestaurant();
    }

    /**
     * @return the total spent across all restaurants in the restaurantMap
     */
    public double getTotal(){
        return restaurantMapObserver.getTotal();
    }
}
