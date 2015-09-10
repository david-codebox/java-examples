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
 * Time: 17:42
 */
public class CompareChangeNotifications {
    public static void main(String[] args) {
        // Use Java Collections to create the List
        List<String> list = new ArrayList<>();
        list.add("d");
        list.add("b");
        list.add("a");
        list.add("c");

        // Now add observability by wrapping it with ObservableList
        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener((ListChangeListener<? super String>) change -> System.out.println("Change detected"));

        // Sort using FXCollections
//        FXCollections.sort(observableList);

        //Sort using Collections
        Collections.sort(observableList);
    }
}
