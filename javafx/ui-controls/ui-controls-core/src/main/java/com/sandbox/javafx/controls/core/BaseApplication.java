package com.sandbox.javafx.controls.core;

import com.google.common.base.Strings;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/11 <br>
 * Time: 16:24 <br>
 * </div>
 */
public abstract class BaseApplication extends Application {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected Pane root;
    protected String title;

    public BaseApplication() {
        root = new VBox(10);
    }

    protected abstract void doInitialization() throws Exception;

    @Override
    public final void init() throws Exception {
        doInitialization();
        if (root.getChildren().isEmpty()) {
            throw new IllegalStateException("No node is added in root content pane. Please add child nodes!");
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root);
        if (!Strings.isNullOrEmpty(title)) {
            primaryStage.setTitle(title);
        }
        primaryStage.setScene(scene);
        primaryStage.setWidth(1027);
        primaryStage.setHeight(768);
        primaryStage.show();
    }

    protected void addNode(Node child) {
        logger.debug("BaseApplication.addNode: Adding child node: [{}]", child.getClass().getName() );
        root.getChildren().add(child);
    }

/*    public static void main(String[] args) {
        launch(args);
    }*/
}
