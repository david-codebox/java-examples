package com.sandbox.designpatterns.observer.example4;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 10:57
 */
public class ObserverPatternTest {

    public static void main(String[] args) {
        //create subject
        MyTopic topic = new MyTopic();
        topic.setName("pre-process");

        //create observers
        Observer obj1 = new MyTopicSubscriber("Obj1");
        Observer obj2 = new MyTopicSubscriber("Obj2");
        Observer obj3 = new MyTopicSubscriber("Obj3");

        //addObserver observers to the subject
        topic.addObserver(obj1);
        topic.addObserver(obj2);
        topic.addObserver(obj3);
        topic.addObserver(subject -> {
            System.out.printf("Preparing to execute workflow [%s]\n", subject.getName());
        });

        //attach observer to subject
//        obj1.setSubject(topic);
//        obj2.setSubject(topic);
//        obj3.setSubject(topic);

        //check if any update is available
        obj1.update(topic);

        //now send message to subject
        topic.postMessage(String.format("New message @%s", LocalDateTime.now()));
    }

}
