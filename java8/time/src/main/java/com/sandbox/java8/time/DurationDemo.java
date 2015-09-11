package com.sandbox.java8.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/11
 * Time: 13:49
 */
public class DurationDemo {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        System.out.println(start);
        LocalDateTime finish=start.plusMinutes(25);
        Duration duration = Duration.between(start, finish);
        System.out.println(duration);
    }
}
