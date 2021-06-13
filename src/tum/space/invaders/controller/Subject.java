package tum.space.invaders.controller;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Subject {

    private final Set<Observer> observers = new HashSet<>();

    public void addObserver(Observer observer) {
        Objects.requireNonNull(observer);
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        Objects.requireNonNull(observer);
        observers.remove(observer);
    }

    protected void notifyObservers() {
        observers.forEach(Observer::update);
    }

}
