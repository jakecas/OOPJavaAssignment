package oop.assignment.restaurant.exceptions;

import oop.assignment.restaurant.objects.OrderType;

public class InvalidOrderTypeException extends RestaurantException{
    public InvalidOrderTypeException(OrderType orderType){
        super("'" + orderType.name() + "' is an invalid order type for this operation.");
    }
}
