package com.hp.sandbox.javax.databinding;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/3/27
 * Time: 17:03
 */
public class UsingFluent {
    public static void main(String[] args) {
        IntegerProperty num1 = new SimpleIntegerProperty(1);
        IntegerProperty num2 = new SimpleIntegerProperty(2);
        NumberBinding sum = num1.add(num2);
        System.out.println(sum.getValue());
        num1.set(20);
        System.out.println(sum.getValue());
    }
}
