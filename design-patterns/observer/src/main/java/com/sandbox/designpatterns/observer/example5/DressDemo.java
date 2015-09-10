package com.sandbox.designpatterns.observer.example5;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/9
 * Time: 22:39
 */
public class DressDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(DressDemo.class);
    public static void main(String[] args) throws InterruptedException {
        RedDress dress = new RedDress();

        LOGGER.debug("DressDemo.main: dress for sale: [{}]", dress );
        for (int i = 0; i < 5; i++) {
            dress.addObserver(new User(dress, String.format("user%d-%s", i,RandomStringUtils.randomAlphabetic(6))));
        }

        Random r = new Random();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        for (int i = 500; i < 5000; i+=500) {
            LOGGER.debug("DressDemo.main: current index [{}]", i);
            int value = r.nextInt(9999);
            LOGGER.debug("DressDemo.main: random value: [{}]", value);
            Thread.sleep(i / 8);
            if (value % 3 == 0) {
                LOGGER.warn("DressDemo.main: Red dress in stock");
                dress.setInStock(true);
            }
            System.out.println("========================================");
        }
    }
}
