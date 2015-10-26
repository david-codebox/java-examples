package com.sandbox.annotation.annotation;

import com.sandbox.annotation.client.FTPTask1;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 21:42
 */
public @interface ComplicatedAnnotation {
    String value();
    boolean validate() default false;
    int size() default -1;
    char codePoint() default 0;
    TaskModule task();
}
