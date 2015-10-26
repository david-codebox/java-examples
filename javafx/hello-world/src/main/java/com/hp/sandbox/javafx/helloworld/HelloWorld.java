package com.hp.sandbox.javafx.helloworld;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/3/26
 * Time: 15:46
 */
public class HelloWorld extends Application {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    private ExecutorService executor= Executors.newCachedThreadPool();
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed. An application may
     * override this method to perform initialization prior to the actual starting
     * of the application.
     * <p>
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     * <p>
     * <p>
     * NOTE: This method is not called on the JavaFX Application Thread. An
     * application must not construct a Scene or a Stage in this
     * method.
     * An application may construct other JavaFX objects in this method.
     * </p>
     */
    @Override
    public void init() throws Exception {
        logger.debug("{} being initialized in init() method", this.getClass().getName());
    }

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     * <p>
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     */
    @Override
    public void stop() throws Exception {
        logger.debug("{} being stopped...", this.getClass().getName());
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p/>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.debug("Starting JavaFX application {}", getClass().getName());
        primaryStage.setTitle("Hello World Javafx");

        Button button = new Button();
        button.setText("Say 'hello world");
//        button.setOnAction(event -> System.out.println("Hello world"));
        button.setOnAction(this::sayHi);

        StackPane root = new StackPane();
        root.getChildren().add(button);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }


    private void sayHi(ActionEvent event) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            logger.debug("HelloWorld.sayHi: Adding thread #[{}]", i+1);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    logger.debug("HelloWorld.run: [{}] - running", name);
                    try {
                        int sleep = 500 * random.nextInt(10);
                        logger.debug("HelloWorld.run: [{}] - sleep time: {}", name,sleep);
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.debug("HelloWorld.run: [{}] - done", name);
                }
            });

        }
    }
}
