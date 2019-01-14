package oop.assignment.restaurant.exceptions;

import oop.assignment.restaurant.objects.OrderType;

/**
 * The exception thrown when an order of a certain type is made to a restaurant which does not offer that type.
 */
public class IncompatibleOrderTypeException extends RestaurantException {
    /**
     * Passes a built message with the details passed to the parent constructor.
     *
     * @param restaurantName name of the restaurant
     * @param orderType order type of the invalid order
     */
    public IncompatibleOrderTypeException(String restaurantName, OrderType orderType){
        super("'" + restaurantName + "' does not offer " + orderType.name());
    }
}
