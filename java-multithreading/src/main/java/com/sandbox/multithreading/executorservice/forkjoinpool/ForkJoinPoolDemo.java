package com.sandbox.multithreading.executorservice.forkjoinpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2016/3/21 <br>
 * Time: 21:59 <br>
 * </div>
 */

public class ForkJoinPoolDemo {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService executor = Executors.newWorkStealingPool();
    private int size;
    private Random random=new Random();
    public ForkJoinPoolDemo(int size) {
        this.size = size;
    }

    public ForkJoinPoolDemo run() throws ExecutionException, InterruptedException {
        if(size <1)size=258;
        List<Callable<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final int finalI = i;
            futures.add(() -> {
                int duration = random.nextInt(10);
                if (duration == 0) {
                    throw new IllegalArgumentException("duration cannot be null!");
                }
                System.out.print(String.format("\n%s - @%d=[%d]>> ", Thread.currentThread().getName(), finalI, duration));
                for (int j = 0; j < duration; j++) {
                    System.out.print(String.format("->%d#",finalI));
                    Thread.sleep(j * 1000);
                }
                System.out.print("<<\n");
                return duration;
            });
        }

        List<Future<Integer>> results=executor.invokeAll(futures);

        int count=0;
        for (Future<Integer> future : results) {
            count += future.get();
            System.out.println("Status of future : " + future.isDone() + ". Result of future : " + future.get());
        }
        logger.info("ForkJoinPoolDemo.run: Total sleeping time:  {} seconds.", count );
        return this;
    }

    public void shutdown() throws InterruptedException {
        logger.debug("ForkJoinPoolDemo.shutdown: [{}] threads in total.", size );

        executor.shutdown();
        executor.awaitTermination(20, TimeUnit.SECONDS);
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPoolDemo demo = new ForkJoinPoolDemo(10);
        demo.run().shutdown();
    }
}
