package com.hp.sandbox.javax.databinding;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/3/27
 * Time: 16:58
 */
public class UsingChangeListener {
    public static void main(String[] args) {
        Bill electricBill = new Bill();

        electricBill.amountDueProperty().addListener(new ChangeListener(){
            @Override public void changed(ObservableValue o,Object oldVal,
                                          Object newVal){
                System.out.printf("Electric bill has changed! new value: %s\n", newVal);
            }
        });

        electricBill.setAmountDue(100.00);
    }
}
