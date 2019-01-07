package oop.assignment;

import oop.assignment.exceptions.ArgumentNumberException;
import oop.assignment.exceptions.InvalidFormatException;
import oop.assignment.exceptions.MisplacedCommandException;
import oop.assignment.exceptions.NonExistentCommandException;
import oop.assignment.restaurant.RestaurantMapController;
import oop.assignment.restaurant.objects.Restaurant;

import java.util.List;

public class RestaurantAppConnector {
    private static final RestaurantAppConnector SINGLETON = new RestaurantAppConnector();

    private RestaurantMapController restaurantMapController;

    private RestaurantAppConnector(){
        restaurantMapController = RestaurantMapController.getInstance();
    }

    public static RestaurantAppConnector getInstance(){
        return SINGLETON;
    }


    public void executeLine(List<String> args){
        switch (args.get(0)){
            case "BeginRestaurant":
                beginRestaurant(args);
                break;
            case "Item":
                addItem(args);
                break;
            case "EndRestaurant":
                endRestaurant(args);
                break;
            case "BeginOrderList":
                beginOrderList(args);
                break;
            case "BeginOrder":
                beginOrder(args);
                break;
            case "OrderItem":
                orderItem(args);
                break;
            case "EndOrder":
                endOrder(args);
                break;
            case "EndOrderList":
                endOrderList(args);
                break;
            case "":
                break;
            default:
                throw new NonExistentCommandException(args.get(0));
        }
    }

    private void beginRestaurant(List<String> args){
        checkArgumentNumber(args.size(), 3, "create restaurant");

        if(restaurantMapController.isRestaurantSessionOpen()){
            throw new MisplacedCommandException("The current restaurant needs to be closed before another can be opened.");
        }
        if(!restaurantMapController.isOrderListNull()){
            throw new MisplacedCommandException("An order is being edited, a restaurant cannot be created..");
        }

        restaurantMapController.addRestaurant(args.get(1), args.get(2));
    }

    private void addItem(List<String> args){
        checkArgumentNumber(args.size(), 3, "create menu item");

        if(!restaurantMapController.isRestaurantSessionOpen()){
            throw new MisplacedCommandException("No restaurant is open to attach item to.");
        }

        try {
            restaurantMapController.addMenuItem(args.get(1), Double.parseDouble(args.get(2)));
        } catch(NumberFormatException e){
            throw new InvalidFormatException("'" + args.get(2) + "' not a valid price for '" + args.get(1) + "'.");
        }
    }

    private void endRestaurant(List<String> args){
        checkArgumentNumber(args.size(), 1, "end restaurant creation");

        if(!restaurantMapController.isRestaurantSessionOpen()){
            throw new MisplacedCommandException("No restaurant is open to end restaurant creation.");
        }

        restaurantMapController.closeCurrentRestaurant();
    }

    private void beginOrderList(List<String> args){
        checkArgumentNumber(args.size(), 1, "begin order list");

        if(restaurantMapController.isRestaurantSessionOpen()){
            throw new MisplacedCommandException("The current restaurant needs to be closed before an order list can be opened.");
        }
        if(!restaurantMapController.isOrderListNull()){
            throw new MisplacedCommandException("The current orderlist needs to be closed before another can be opened.");
        }

        restaurantMapController.startNewOrderList();
    }

    private void beginOrder(List<String> args){
        checkArgumentNumber(args.size(), 3, "begin an order");

        if(restaurantMapController.isOrderListNull()){
            throw new MisplacedCommandException("An orderlist needs to be opened before an order can be added.");
        }

        restaurantMapController.startNewOrder(args.get(1), args.get(2));
    }

    private void orderItem(List<String> args){
        checkArgumentNumber(args.size(), 2, "order item");

        if(restaurantMapController.isOrderListEmpty()){
            throw new MisplacedCommandException("An order needs to be opened before an item can be added to it.");
        }

        restaurantMapController.addItemToCurrentOrder(args.get(1));
    }

    private void endOrder(List<String> args){
        checkArgumentNumber(args.size(), 1, "end order");

        if(restaurantMapController.isOrderListEmpty()){
            throw new MisplacedCommandException("An order needs to be opened before an item can be added to it.");
        }

        restaurantMapController.closeCurrentOrder();
    }

    private void endOrderList(List<String> args){
        checkArgumentNumber(args.size(), 1, "end order list");

        if(restaurantMapController.isOrderListNull()){
            throw new MisplacedCommandException("An order list needs to be opened before it can be closed.");
        }
        System.out.println("The total price of this order list is: " + restaurantMapController.closeOrderList());
    }

    private void checkArgumentNumber(int actual, int expected, String action){
        if(actual != expected){
            throw new ArgumentNumberException("Invalid number of arguments to " + action + ": ", actual);
        }
    }

    public void printTotal(){
        System.out.println("The total spent this session was " + restaurantMapController.getTotal());
    }

    public void printHighestRevenuRestaurant(){
        Restaurant restaurant = restaurantMapController.getHighestRevenueRestaurant();
        System.out.println("The restaurant with the highest revenue was '" + restaurant.getName() + "' with " + restaurant.getRevenue() + ".");
    }
}
