package com.sandbox.designpatterns.observer.example5;

import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/9
 * Time: 22:30
 */
public class RedDress implements Observable {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private List<Observer> users = new CopyOnWriteArrayList<>();
    private boolean inStock = true;
    private String name=String.format("dress %s", RandomStringUtils.randomAlphabetic(3));

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
        notifyObserver();
    }

    @Override
    public void addObserver(Observer o) {
        logger.debug("RedDress.addObserver: Adding observer [{}]", o );
        users.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        logger.debug("RedDress.removeObserver: removing observer [{}]", o );
        users.remove(o);
    }

    @Override
    public void notifyObserver() {
        logger.warn("RedDress.notifyObserver: Notifying all users" );
        users.forEach(user -> user.update(this));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("name", name)
                .toString();
    }
}
