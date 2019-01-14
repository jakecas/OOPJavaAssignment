package oop.assignment.restaurant.objects;

/**
 * A MenuItem is an item in the list of items that a restaurant sells,
 * and is what is added to orders.
 */
public class MenuItem {
    /**
     * the name of the item
     */
    private String name;
    /**
     * the price of the item
     */
    private double price;

    /**
     * Initialises the name and price of this item.
     *
     * @param name the name of the item
     * @param price the price of the item
     */
    public MenuItem(String name, double price){
        this.name = name;
        this.price = price;
    }

    /**
     * @return the name of this item
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price of this item
     */
    public double getPrice() {
        return price;
    }
}
