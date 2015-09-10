package com.sandbox.designpatterns.observer.example1;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 10:48
 *
 * http://www.journaldev.com/1739/observer-design-pattern-in-java-example-tutorial
 */
public interface Subject {
    void register(Observer observer);

    void unregister(Observer observer);

    void notifyObservers();

    Object getUpdate(Observer observer);
}
