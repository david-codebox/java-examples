package com.sandbox.javafx.controls.dialogs;

import javafx.scene.control.Alert;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/15
 * Time: 9:53
 */
public class ExceptionDialog extends Alert {

    public ExceptionDialog() {
        super(AlertType.ERROR);
        setTitle("Exception Dialog");
        setHeaderText("Exception occurred!");

    }
}
