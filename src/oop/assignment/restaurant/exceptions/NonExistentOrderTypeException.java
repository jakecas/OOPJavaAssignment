package oop.assignment.restaurant.exceptions;

/**
 * The exception thrown when an OrderType that doesn't exist is used.
 */
public class NonExistentOrderTypeException extends RestaurantException {
    /**
     * Passes the built message with the details passed to the parent constructor.
     *
     * @param orderTypeText the non-existent OrderType
     */
    public NonExistentOrderTypeException(String orderTypeText){
        super("Order type '" + orderTypeText + "' does not exist.");
    }
}
