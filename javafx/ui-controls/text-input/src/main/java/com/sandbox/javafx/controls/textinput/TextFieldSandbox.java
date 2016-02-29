package com.sandbox.javafx.controls.textinput;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/12/3 <br>
 * Time: 23:01 <br>
 * </div>
 */
public class TextFieldSandbox extends Application {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private VBox root;

    @Override
    public void init() throws Exception {
        root = new VBox();
        TextField textField = new TextField();
        textField.setPromptText("this is prompt");
        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logger.debug("TextFieldSandbox.handle: On Action" );
            }
        });

        textField.setOnMouseClicked(e -> {
            logger.debug("TextFieldSandbox.init: Mouse clicked!" );
        });
        root.getChildren().add(textField);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(root));
        stage.show();
    }
}
