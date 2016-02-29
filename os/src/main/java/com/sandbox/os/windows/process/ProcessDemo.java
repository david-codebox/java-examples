package com.sandbox.os.windows.process;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2016/2/17 <br>
 * Time: 16:48 <br>
 * </div>
 */

public class ProcessDemo {
    public static void main(String[] args) {
        try {
            // create a new process
            System.out.println("Creating Process...");
            Process p = Runtime.getRuntime().exec("notepad.exe");

            // wait 10 seconds
            System.out.println("Waiting...");
            Thread.sleep(10000);

            // kill the process
            p.destroy();
            System.out.println("Process destroyed.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
