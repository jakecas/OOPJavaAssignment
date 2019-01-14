package oop.assignment.restaurant.exceptions;

public class NonExistentRestaurantException extends RestaurantException {
    public NonExistentRestaurantException(String restaurantName){
        super("Restaurant '" + restaurantName + "' does not exist.");
    }
}
