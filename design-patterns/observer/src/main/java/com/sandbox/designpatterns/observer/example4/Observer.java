package com.sandbox.designpatterns.observer.example4;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 10:49
 */
@FunctionalInterface
public interface Observer {
    void update(Subject subject);
}
