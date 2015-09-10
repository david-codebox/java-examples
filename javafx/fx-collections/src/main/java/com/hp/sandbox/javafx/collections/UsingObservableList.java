package com.hp.sandbox.javafx.collections;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/3/27
 * Time: 17:36
 */
public class UsingObservableList {
    public static void main(String[] args) {

        // Use Java Collections to create the List.
        List<String> list = new ArrayList<>();

        // Now add observability by wrapping it with ObservableList.
        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener((ListChangeListener) change -> System.out.println("Detected a change! "));

        // Changes to the observableList WILL be reported.
        // This line will print out "Detected a change!"
        observableList.add("item one");

        // Changes to the underlying list will NOT be reported
        // Nothing will be printed as a result of the next line.
        list.add("item two");

        System.out.println("Size: "+observableList.size());
    }
}
