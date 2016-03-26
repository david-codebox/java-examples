package com.sandbox.core;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2016/3/16 <br>
 * Time: 17:02 <br>
 * </div>
 */

public class SystemProperty {
    public static void main(String[] args) {
        String version = System.getProperty("java.version");
        System.out.println(version);
    }
}
