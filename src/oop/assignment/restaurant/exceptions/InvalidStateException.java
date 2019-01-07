package oop.assignment.restaurant.exceptions;

public class InvalidStateException extends RestaurantException {
    public InvalidStateException(String state, String operation){
        super("'" + state + "' is an invalid state for " + operation + ".");
    }
}
