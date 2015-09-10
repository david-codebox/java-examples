package com.sandbox.designpatterns.observer.example1;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 10:49
 */
public interface Observer {
    void update();

    void setSubject(Subject subject);
}
