package oop.assignment.restaurant.exceptions;

import oop.assignment.restaurant.objects.OrderType;


/**
 * The exception thrown when an operation is attempted with an invalid OrderType,
 * namely creating an Order with OrderType.BOTH
 */
public class InvalidOrderTypeException extends RestaurantException{
    /**
     * Passes a built message to the parent constructor.
     *
     * @param orderType the invalid OrderType
     */
    public InvalidOrderTypeException(OrderType orderType){
        super("'" + orderType.name() + "' is an invalid order type for this operation.");
    }
}
