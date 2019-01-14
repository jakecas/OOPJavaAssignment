package oop.assignment.restaurant.observables;

import oop.assignment.restaurant.observers.IObserver;

/**
 * This is the subject of the observer design pattern,
 * it defines the methods that need to be implemented by any subject of the relationship.
 * The Observable would also need to hold a list of observers to manipulate.
 */
public interface IObservable {
    /**
     * Adds the passed Observer to the list of active observers of this object.
     *
     * @param observer the observer to add to the list
     */
    void register(IObserver observer);

    /**
     * Removes the passed Observer from the list of active observers of this object.
     *
     * @param observer the observer to remove from the list
     */
    void unregister(IObserver observer);

    /**
     * Iterates over the list of observers, calling the update method on each one.
     */
    void notifyObservers();
}
