package com.sandbox.designpatterns.observer.example5;

import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/9
 * Time: 22:35
 */
public class User implements Observer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private UUID uuid=UUID.randomUUID();
    private Observable observable;

    private String name;

    public User(Observable observable, String name) {
        this.observable = observable;
        this.name = name;
    }

    public User(Observable observable) {
        this.observable = observable;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Observable getObservable() {
        return observable;
    }

    public void setObservable(Observable observable) {
        this.observable = observable;
    }

    @Override
    public void update(Observable subject) {
        buyDress(subject);
//        unsubscribe();
    }

    public void buyDress(Observable subject) {
        logger.info("User.buyDress: [{}] buying dress [{}]", this.getName(), subject.getName() );
    }

    public void unsubscribe() {
        observable.removeObserver(this);
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("uuid", uuid)
                .toString();
    }
}
