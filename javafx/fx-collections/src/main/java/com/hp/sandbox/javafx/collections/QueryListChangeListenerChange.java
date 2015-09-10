package com.hp.sandbox.javafx.collections;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/3/27
 * Time: 17:50
 */
public class QueryListChangeListenerChange {
    public static void main(String[] args) {
        // Use Java Collections to create the List
        List<String> list = new ArrayList<String>();
        list.add("d");
        list.add("b");
        list.add("a");
        list.add("c");

        // Now add observability by wrapping it with ObservableList
        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener((ListChangeListener<String>) change -> {
            System.out.println("Detected a change! ");
            while (change.next()) {
                System.out.println("Was added? " + change.wasAdded());
                System.out.println("Was removed? " + change.wasRemoved());
                System.out.println("Was replaced? " + change.wasReplaced());
                System.out.println("Was permutated? " + change.wasPermutated());
            }
        });

        // Sort using FXCollections
//        FXCollections.sort(observableList);

        Collections.sort(observableList);
    }
}
