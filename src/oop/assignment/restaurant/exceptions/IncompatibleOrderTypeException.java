package oop.assignment.restaurant.exceptions;

import oop.assignment.restaurant.objects.OrderType;

public class IncompatibleOrderTypeException extends RuntimeException {
    public IncompatibleOrderTypeException(String restaurantName, OrderType orderType){
        super("'" + restaurantName + "' does not offer " + orderType.name());
    }
}
