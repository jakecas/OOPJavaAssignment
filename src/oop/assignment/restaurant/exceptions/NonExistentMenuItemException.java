package oop.assignment.restaurant.exceptions;

/**
 * The exception thrown when an item that does not exist for a specific restaurant is called.
 */
public class NonExistentMenuItemException extends RestaurantException {
    /**
     * Passes a built string to the parent constructor.
     *
     * @param itemName the name of the non-existent item
     */
    public NonExistentMenuItemException(String itemName){
        super("Menu item '" + itemName + "' does not exist for this restaurant.");
    }
}
