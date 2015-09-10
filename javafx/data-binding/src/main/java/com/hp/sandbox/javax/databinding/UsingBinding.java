package com.hp.sandbox.javax.databinding;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/3/27
 * Time: 17:06
 */
public class UsingBinding {
    public static void main(String[] args) {
        IntegerProperty num1 = new SimpleIntegerProperty(1);
        IntegerProperty num2 = new SimpleIntegerProperty(2);
        NumberBinding sum = Bindings.add(num1, num2);
        System.out.println(sum.getValue());
        num1.setValue(2);
        System.err.println(sum.getValue());
    }
}
