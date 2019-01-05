package oop.assignment.restaurant.exceptions;

public class NonExistentMenuItemException extends RuntimeException {
    public NonExistentMenuItemException(String itemName){
        super("Menu item '" + itemName + "' does not exist for this restaurant.");
    }
}
