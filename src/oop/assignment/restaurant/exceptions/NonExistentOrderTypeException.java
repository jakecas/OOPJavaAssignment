package oop.assignment.restaurant.exceptions;

public class NonExistentOrderTypeException extends RuntimeException {
    public NonExistentOrderTypeException(String orderTypeText){
        super("Order type '" + orderTypeText + "' does not exist.");
    }
}
