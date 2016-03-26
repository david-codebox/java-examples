package com.sandbox.core.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2016/3/8 <br>
 * Time: 9:15 <br>
 * </div>
 */

public class ScheduledExecutorServiceDemo {
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private TimeUnit unit;

    public ScheduledExecutorServiceDemo() {
        unit = TimeUnit.SECONDS;
    }

    public void demo(long period) {

        executor.scheduleAtFixedRate(() -> {
            System.out.println("Thread running in intervals...");
        }, 0, period, unit);
    }

    public static void main(String[] args) {
        new ScheduledExecutorServiceDemo().demo(5);
    }
}
