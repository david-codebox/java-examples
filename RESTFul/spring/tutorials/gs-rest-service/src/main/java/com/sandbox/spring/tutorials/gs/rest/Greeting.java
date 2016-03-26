package com.sandbox.spring.tutorials.gs.rest;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2016/3/26 <br>
 * Time: 9:53 <br>
 * </div>
 */

public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
