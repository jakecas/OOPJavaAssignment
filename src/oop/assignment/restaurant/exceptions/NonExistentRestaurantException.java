package oop.assignment.restaurant.exceptions;

/**
 * The exception thrown when an restaurant that doesn't exist is used.
 */
public class NonExistentRestaurantException extends RestaurantException {
    /**
     * Passes the built message with the details passed to the parent constructor.
     *
     * @param restaurantName the non-existent restaurant's name
     */
    public NonExistentRestaurantException(String restaurantName){
        super("Restaurant '" + restaurantName + "' does not exist.");
    }
}
