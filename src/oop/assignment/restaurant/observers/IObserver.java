package oop.assignment.restaurant.observers;

/**
 * The observer of the Observer design pattern, which just defines the update method for the observers.
 * In this implementation, update takes no parameters,
 * as it was decided to initialise the observers with a reference to the subject for ease of access to its attributes.
 */
public interface IObserver {
    /**
     * Checks the state of the subject and updates this object accordingly.
     */
    void update();
}
