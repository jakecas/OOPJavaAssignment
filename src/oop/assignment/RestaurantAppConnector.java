package oop.assignment;

import oop.assignment.exceptions.ArgumentNumberException;
import oop.assignment.exceptions.InvalidFormatException;
import oop.assignment.exceptions.MisplacedCommandException;
import oop.assignment.exceptions.NonExistentCommandException;
import oop.assignment.restaurant.RestaurantAppController;
import oop.assignment.restaurant.objects.Restaurant;

import java.util.List;

/**
 * A singleton object that maps the text file commands to methods,
 * which in turn connect to the RestaurantAppController's provided methods for manipulating the system.
 */
public class RestaurantAppConnector {
    /**
     * the singleton instance of this class
     */
    private static final RestaurantAppConnector SINGLETON = new RestaurantAppConnector();

    /**
     * the singleton instance of the app controller
     */
    private RestaurantAppController restaurantAppController;

    /**
     * The constructor is set to be private for the purposes of the singleton design pattern.
     *
     * Otherwise, it just gets the instance of the RestaurantAppController
     */
    private RestaurantAppConnector(){
        restaurantAppController = RestaurantAppController.getInstance();
    }

    /**
     * @return the singleton instance of this class
     */
    public static RestaurantAppConnector getInstance(){
        return SINGLETON;
    }


    /**
     * This method maps the commands to the appropriate method in this class.
     * If the command does not exist, it throws an NonExistentCommandException.
     *
     * @param args the command split into a list of words
     */
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

    /**
     * Checks that the command is of the appropriate size, that there is no open restaurant session,
     * and that no orderlist is being used.
     *
     * If all of the above is in order, it tries to pass the restaurant details from the command to the controller,
     * otherwise, it throws an ArgumentNumberException or a MisplacedCommandException.
     *
     * @param args the command split into a list of words
     */
    private void beginRestaurant(List<String> args){
        checkArgumentNumber(args.size(), 3, "create restaurant");

        if(restaurantAppController.isRestaurantSessionOpen()){
            throw new MisplacedCommandException("The current restaurant needs to be closed before another can be opened.");
        }
        if(!restaurantAppController.isOrderListNull()){
            throw new MisplacedCommandException("An order is being edited, a restaurant cannot be created..");
        }

        restaurantAppController.addRestaurant(args.get(1), args.get(2));
    }

    /**
     * Checks that the command is of the appropriate size and that there is an open restaurant session.
     *
     * If all of the above is in order, it tries to pass the menu item details from the command to the controller,
     * otherwise, it throws an ArgumentNumberException or a MisplacedCommandException.
     *
     * If the price of the item is not numeric, it throws an InvalidFormatException.
     *
     * @param args the command split into a list of words
     */
    private void addItem(List<String> args){
        checkArgumentNumber(args.size(), 3, "create menu item");

        if(!restaurantAppController.isRestaurantSessionOpen()){
            throw new MisplacedCommandException("No restaurant is open to attach item to.");
        }

        try {
            restaurantAppController.addMenuItem(args.get(1), Double.parseDouble(args.get(2)));
        } catch(NumberFormatException e){
            throw new InvalidFormatException("'" + args.get(2) + "' not a valid price for '" + args.get(1) + "'.");
        }
    }

    /**
     * Checks that the command is of the appropriate size and that there is an open restaurant session.
     *
     * If all of the above is in order, it tries to close the current restaurant session,
     * otherwise, it throws an ArgumentNumberException or a MisplacedCommandException.
     *
     * @param args the command split into a list of words
     */
    private void endRestaurant(List<String> args){
        checkArgumentNumber(args.size(), 1, "end restaurant creation");

        if(!restaurantAppController.isRestaurantSessionOpen()){
            throw new MisplacedCommandException("No restaurant is open to end restaurant creation.");
        }

        restaurantAppController.closeCurrentRestaurant();
    }

    /**
     * Checks that the command is of the appropriate size, that there is no open restaurant session,
     * and that no order list is open.
     *
     * If all of the above is in order, it tries to open a new order list,
     * otherwise, it throws an ArgumentNumberException or a MisplacedCommandException.
     *
     * @param args the command split into a list of words
     */
    private void beginOrderList(List<String> args){
        checkArgumentNumber(args.size(), 1, "begin order list");

        if(restaurantAppController.isRestaurantSessionOpen()){
            throw new MisplacedCommandException("The current restaurant needs to be closed before an order list can be opened.");
        }
        if(!restaurantAppController.isOrderListNull()){
            throw new MisplacedCommandException("The current orderlist needs to be closed before another can be opened.");
        }

        restaurantAppController.startNewOrderList();
    }

    /**
     * Checks that the command is of the appropriate size and that an order list is open.
     *
     * If all of the above is in order, it tries start a new order with the details in the command,
     * otherwise, it throws an ArgumentNumberException or a MisplacedCommandException.
     *
     * @param args the command split into a list of words
     */
    private void beginOrder(List<String> args){
        checkArgumentNumber(args.size(), 3, "begin an order");

        if(restaurantAppController.isOrderListNull()){
            throw new MisplacedCommandException("An orderlist needs to be opened before an order can be added.");
        }

        restaurantAppController.startNewOrder(args.get(1), args.get(2));
    }

    /**
     * Checks that the command is of the appropriate size and that an order is open.
     *
     * If all of the above is in order,
     * it tries start add an item to the current order with the details in the command,
     * otherwise it throws an ArgumentNumberException or a MisplacedCommandException.
     *
     * @param args the command split into a list of words
     */
    private void orderItem(List<String> args){
        checkArgumentNumber(args.size(), 2, "order item");

        if(restaurantAppController.isOrderListEmpty()){
            throw new MisplacedCommandException("An order needs to be opened before an item can be added to it.");
        }

        restaurantAppController.addItemToCurrentOrder(args.get(1));
    }

    /**
     * Checks that the command is of the appropriate size and that an order is open.
     *
     * If all of the above is in order, it tries to close the current order,
     * otherwise, it throws an ArgumentNumberException or a MisplacedCommandException.
     *
     * @param args the command split into a list of words
     */
    private void endOrder(List<String> args){
        checkArgumentNumber(args.size(), 1, "end order");

        if(restaurantAppController.isOrderListEmpty()){
            throw new MisplacedCommandException("An order needs to be opened before an item can be added to it.");
        }

        restaurantAppController.closeCurrentOrder();
    }

    /**
     * Checks that the command is of the appropriate size and that an orderlist is open.
     *
     * If all of the above is in order, it tries to close the current orderllist and print the total price,
     * otherwise, it throws an ArgumentNumberException or a MisplacedCommandException.
     *
     * @param args the command split into a list of words
     */
    private void endOrderList(List<String> args){
        checkArgumentNumber(args.size(), 1, "end order list");

        if(restaurantAppController.isOrderListNull()){
            throw new MisplacedCommandException("An order list needs to be opened before it can be closed.");
        }
        System.out.println("The total price of this order list is: " + restaurantAppController.closeOrderList());
    }

    /**
     * Checks if the actual and expected number of arguments is the same.
     * If not, it throws an ArgumentNumberException using the action parameter to provide further detail.
     *
     * @param actual the actual number of arguments
     * @param expected the expected number of arguments
     * @param action the action to describe in the exception, should it occur
     */
    private void checkArgumentNumber(int actual, int expected, String action){
        if(actual != expected){
            throw new ArgumentNumberException("Invalid number of arguments to " + action + ": ", actual);
        }
    }

    /**
     * Prints the message with the total spent so far.
     * In this case it is only run at the end of the file.
     */
    public void printTotal(){
        System.out.println("The total spent this session was " + restaurantAppController.getTotal());
    }

    /**
     * Prints the details of the restaurant with the highest revenue,
     * if any restaurants exist and if any restaurant made any revenue.
     */
    public void printHighestRevenuRestaurant() {
        Restaurant restaurant = restaurantAppController.getHighestRevenueRestaurant();
        if (restaurant == null) {
            System.out.println("No restaurants exist.");
        } else if(restaurant.getName().equals("dummy")) {
            System.out.println("No restaurant made any revenue.");
        } else {
            System.out.println("The restaurant with the highest revenue was '" + restaurant.getName() + "' with " + restaurant.getRevenue() + ".");
        }
    }
}
