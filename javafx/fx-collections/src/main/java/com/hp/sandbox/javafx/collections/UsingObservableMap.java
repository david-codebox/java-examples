package com.hp.sandbox.javafx.collections;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/3/27
 * Time: 17:39
 */
public class UsingObservableMap {
    public static void main(String[] args) {
        // Use Java Collections to create the List.
        Map<String,String> map = new HashMap<String,String>();

        // Now add observability by wrapping it with ObservableList.
        ObservableMap<String,String> observableMap = FXCollections.observableMap(map);

        observableMap.addListener((MapChangeListener) change -> System.out.println("Detected a change on map"));

        // Changes to the observableMap WILL be reported.
        observableMap.put("key 1","value 1");
        System.out.println("Size: "+observableMap.size());

        // Changes to the underlying map will NOT be reported.
        map.put("key 2","value 2");
        System.out.println("Size: "+observableMap.size());
    }
}
