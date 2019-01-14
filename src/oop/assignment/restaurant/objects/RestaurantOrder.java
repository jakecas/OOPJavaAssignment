package oop.assignment.restaurant.objects;

/**
 * A child of the Order class which simply adds a reference to the restaurant that this order belongs to.
 */
public class RestaurantOrder extends Order {
    /**
     * the restaurant this order belongs to
     */
    private Restaurant restaurant;

    /**
     * Calls the parent constructor and adds the reference to the restaurant.
     *
     * @param orderType the type of order this is
     * @param restaurant the restaurant this order belongs to
     */
    public RestaurantOrder(OrderType orderType, Restaurant restaurant) {
        super(orderType);
        this.restaurant = restaurant;
    }

    /**
     * @return the restaurant this order belongs to
     */
    public Restaurant getRestaurant(){
        return restaurant;
    }

    /**
     * @return the name of the restaurant this order belongs to
     */
    public String getRestaurantName() {
        return restaurant.getName();
    }

}
