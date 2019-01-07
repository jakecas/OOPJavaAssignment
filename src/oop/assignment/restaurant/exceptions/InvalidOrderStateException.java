package oop.assignment.restaurant.exceptions;

public class InvalidOrderStateException extends RestaurantException {
    public InvalidOrderStateException(String orderState, String operation){
        super("'" + orderState + "' is an invalid state for " + operation + ".");
    }
}
