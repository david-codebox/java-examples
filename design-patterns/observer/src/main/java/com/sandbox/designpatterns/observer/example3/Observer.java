package com.sandbox.designpatterns.observer.example3;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 13:19
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
