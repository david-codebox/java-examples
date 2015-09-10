package com.sandbox.designpatterns.observer.example4;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 10:48
 *
 * http://www.journaldev.com/1739/observer-design-pattern-in-java-example-tutorial
 */
public interface Subject {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

    String getName();

    void setName(String name);
}
