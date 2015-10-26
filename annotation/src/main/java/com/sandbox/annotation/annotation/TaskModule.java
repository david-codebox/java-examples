package com.sandbox.annotation.annotation;

import com.sandbox.annotation.Module;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 21:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface TaskModule {
    Module module();
    Module parent() default Module.NONE;
    String moduleId() default "";
}
