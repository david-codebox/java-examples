package com.sandbox.annotation.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 21:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InheritedAnnotation {
    String value() default "test";
}
