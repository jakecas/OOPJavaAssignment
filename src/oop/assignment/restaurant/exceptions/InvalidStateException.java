package oop.assignment.restaurant.exceptions;

/**
 * The exception thrown when an operation is attempted with an invalid state of a variable,
 * such as trying to create an order when one already exists.
 */
public class InvalidStateException extends RestaurantException {
    /**
     * Passes a built string with the details to the parent constructor.
     *
     * @param state the actual state of the object
     * @param operation the operation attempted on the object
     */
    public InvalidStateException(String state, String operation){
        super("'" + state + "' is an invalid state for " + operation + ".");
    }
}
