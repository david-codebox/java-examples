package com.sandbox.os.windows.process;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2016/2/17 <br>
 * Time: 16:48 <br>
 * </div>
 */

public class ProcessBuilderDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("D:\\tools\\BlackArrowU.exe");
        // create a new process
        System.out.println("Creating Process...");
        Process p = builder.start();

        // wait 10 seconds
        System.out.println("Waiting...");
//        Thread.sleep(15000);
        p.waitFor(5, TimeUnit.SECONDS);

        // kill the process
        p.destroy();
        System.out.println("Process destroyed.");

        int returnValue = p.exitValue();
        System.out.printf("return value: %d", returnValue);
    }
}
