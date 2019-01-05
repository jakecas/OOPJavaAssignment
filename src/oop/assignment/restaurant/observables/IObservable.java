package oop.assignment.restaurant.observables;

import oop.assignment.restaurant.observers.IObserver;

public interface IObservable {
    void register(IObserver observer);

    void unregister(IObserver observer);

    void notifyObservers();
}
