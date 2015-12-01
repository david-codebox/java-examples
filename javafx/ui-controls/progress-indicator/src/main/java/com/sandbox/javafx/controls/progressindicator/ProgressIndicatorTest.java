package com.sandbox.javafx.controls.progressindicator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/12/1 <br>
 * Time: 16:16 <br>
 * </div>
 */
public class ProgressIndicatorTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox();
        ProgressIndicator indicator = new ProgressIndicator();
        System.out.printf("Is indeterminate: %s\n",indicator.isIndeterminate());

        hBox.getChildren().add(indicator);
        Scene scene = new Scene(hBox);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
