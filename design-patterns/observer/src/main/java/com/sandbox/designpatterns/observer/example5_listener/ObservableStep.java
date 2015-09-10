package com.sandbox.designpatterns.observer.example5_listener;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 11:25
 */
public interface ObservableStep {
    public void addStepListener(StepListener o);
    public void removeListener(StepListener o);
    public void notifyAllListeners();
}
