package com.sandbox.designpatterns.observer.example4;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 10:55
 */
public class MyTopicSubscriber implements Observer {

    private String name;

    public MyTopicSubscriber(String nm){
        this.name=nm;
    }
    @Override
    public void update(Subject subject) {
        String msg = subject.getName();
        if (msg == null) {
            System.out.println(name + ":: No new message");
        } else
            System.out.println(name + ":: Consuming message::" + msg);
    }

}
