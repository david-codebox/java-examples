package com.sandbox.javafx.controls;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/29
 * Time: 15:46
 */
public class DialogTest extends Application{

    private Dialog<ButtonType> dialog;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        AnchorPane root = new AnchorPane();
        Button button = new Button("OK");
        root.getChildren().add(button);
        dialog = new Dialog<ButtonType>();
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        dialog.getDialogPane().getButtonTypes().add(loginButtonType);
        boolean disabled = false; // computed based on content of text fields, for example
        dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);
        dialog.getDialogPane().setContent(getContent());


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.showAndWait()
                .ifPresent(response -> System.out.printf("OK button pressed! Button caption: %s \n", response.getText()));


            }
        });

    }

    private GridPane getContent() {
        GridPane gridPane = new GridPane();
        gridPane.add(new Button("Button"), 1, 0); // column=1 row=0
        gridPane.add(new Label("Label test"), 2, 0);  // column=2 row=0
        return gridPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
