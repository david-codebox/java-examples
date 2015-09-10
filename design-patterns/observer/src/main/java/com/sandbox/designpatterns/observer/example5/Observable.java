package com.sandbox.designpatterns.observer.example5;


/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/9
 * Time: 22:27
 */
public interface Observable {
    void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();

    String getName();
}
