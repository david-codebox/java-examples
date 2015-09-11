package com.sandbox.java8.time;

import java.time.Instant;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/10
 * Time: 11:21
 */
public class InstantTest {
    public static void main(String[] args) {
        Instant instant = Instant.ofEpochMilli(1439162028810L);
        System.out.println(instant);
    }
}
