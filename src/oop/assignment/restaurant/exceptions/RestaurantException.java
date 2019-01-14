package oop.assignment.restaurant.exceptions;

/**
 * The parent exception of all exceptions in the restaurant package,
 * used for the purposes of organizing them all into one umbrella exception.
 *
 * It extends RuntimeException rather than simply Exception.
 * as all exceptions in this package were designed to be unchecked exceptions.
 * This is due to the errors described being user-dependent, and thus unpredictable.
 */
public class RestaurantException extends RuntimeException {
    /**
     * Passes the message to the parent constructor.
     *
     * @param message the message the exception will give
     */
    public RestaurantException(String message){
        super(message);
    }
}
