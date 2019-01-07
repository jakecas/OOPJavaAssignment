package oop.assignment.restaurant.exceptions;

public class NonExistentOrderTypeException extends RestaurantException {
    public NonExistentOrderTypeException(String orderTypeText){
        super("Order type '" + orderTypeText + "' does not exist.");
    }
}
