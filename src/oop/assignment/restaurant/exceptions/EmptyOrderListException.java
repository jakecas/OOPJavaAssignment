package oop.assignment.restaurant.exceptions;

/**
 * The exception thrown when an action is attempted on an empty order list.
 */
public class EmptyOrderListException extends RestaurantException{
    /**
     * Prepends "Order list is empty; " to the action message passed, and passes it to the parent constructor.
     *
     * @param action action attempted on the empty order list
     */
    public EmptyOrderListException(String action){
        super("Order list is empty; " + action);
    }
}
