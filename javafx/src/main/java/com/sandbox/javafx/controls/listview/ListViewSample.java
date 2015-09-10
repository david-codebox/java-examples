package com.sandbox.javafx.controls.listview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/2
 * Time: 22:52
 */
public class ListViewSample extends Application {
    public static final ObservableList names =
            FXCollections.observableArrayList();
    public static final ObservableList data =
            FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("List View Sample");
        final ListView listView = new ListView(data);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        names.addAll(
                "Adam", "Alex", "Alfred", "Albert",
                "Brenda", "Connie", "Derek", "Donny",
                "Lynne", "Myrtle", "Rose", "Rudolph",
                "Tony", "Trudy", "Williams", "Zach"
        );

        for (int i = 0; i < 18; i++) {
            data.add("anonym"+i);
        }

        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
