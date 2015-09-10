package com.example.generics;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/8
 * Time: 14:32
 */
public class A extends Base {
    protected LocalDateTime dateTime;

    public A(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public A() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
