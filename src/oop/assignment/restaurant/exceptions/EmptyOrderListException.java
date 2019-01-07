package oop.assignment.restaurant.exceptions;

public class EmptyOrderListException extends RestaurantException{
    public EmptyOrderListException(String action){
        super("Order list is empty; " + action);
    }
}
