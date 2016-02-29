package com.sandbox.javafx.controls.buttons;

import com.sandbox.javafx.controls.core.BaseApplication;
import javafx.scene.control.Button;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/12/14 <br>
 * Time: 21:06 <br>
 * </div>
 */

public class ButtonStateDemo extends BaseApplication {
    @Override
    protected void doInitialization() throws Exception {

        Button button = new Button("Click Me");
        addNode(button);
    }
}
