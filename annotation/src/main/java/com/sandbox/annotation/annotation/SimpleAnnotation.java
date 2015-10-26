package com.sandbox.annotation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 21:31
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleAnnotation {
    String value() default "test";
}
