package com.sandbox.annotation.client;

import com.sandbox.annotation.Module;
import com.sandbox.annotation.annotation.ComplicatedAnnotation;
import com.sandbox.annotation.annotation.TaskModule;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 21:46
 */
@ComplicatedAnnotation(value = "hello", validate=true, size = 10, codePoint = '\u0924', task=@TaskModule(module = Module.FTP))
public class ComplicatedClass {
}
