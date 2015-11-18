package com.sandbox.javafx.controls.tableview;

import com.sandbox.javafx.controls.tableview.cells.ConfigValueCellFactory;
import com.sandbox.javafx.controls.tableview.cells.ConfigValueStringConverter;
import com.sandbox.javafx.controls.tableview.cells.ConfigValueValueFactory;
import com.typesafe.config.*;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiConsumer;


/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/17 <br>
 * Time: 14:31 <br>
 * </div>
 */
public class ConfigObjectTableView extends Application {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String Column1MapKey = "KEY";
    public static final String Column2MapKey = "VALUE";
    private final TableView<Map<String, ConfigValue>> table =new TableView<>();
    private final ObservableList<? extends ConfigObject> data;
    final HBox hb = new HBox();

    public ConfigObjectTableView() {
        Config config = ConfigFactory.parseResources("resource.conf");
//        String path = "svn.repositories";
        String path = "ftp.local-working-folders";
        data = FXCollections.observableList(config.getObjectList(path));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group());
        stage.setTitle("Config Object Table");
        stage.setWidth(450);
        stage.setHeight(550);

        final Label label = new Label("Configuration");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        table.autosize();
        table.setItems(generateDataInMap());
        buildColumns(data);


        final Button addButton = new Button("Add");
        final Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e ->{
            table.getItems().forEach(map -> {
                logger.debug("ConfigObjectTableView.start: row: [{}]", map );
            });
        });
/*        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Person(
                    addFirstName.getText(),
                    addLastName.getText(),
                    addEmail.getText()));
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
        });*/

//        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.getChildren().addAll(addButton,submitBtn);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    private void buildColumns(List<? extends ConfigObject> data) {
        ConfigObject configObject = data.stream().findFirst().get();

        configObject.entrySet().forEach(entry -> {
            TableColumn<Map<String, ConfigValue>, ConfigValue> column = new TableColumn<>(entry.getKey());
            column.setCellValueFactory(new ConfigValueValueFactory<>(entry.getKey()));
            setUpCellFactory(column, entry);
            column.setOnEditCommit(t -> {
                t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).put(entry.getKey(), t.getNewValue());
            });
            table.getColumns().add(column);
            table.getColumns().sort((o1, o2) -> o1.getText().compareTo(o2.getText()));
        });
    }

    private void setUpCellFactory(TableColumn<Map<String, ConfigValue>, ConfigValue> column, Map.Entry<String, ConfigValue> entry) {

        switch (entry.getValue().valueType()) {
            case STRING:
                column.setCellFactory(TextFieldTableCell.forTableColumn(new ConfigValueStringConverter()));
                break;
            case BOOLEAN:
                column.setCellFactory(new ConfigValueCellFactory<>(entry));
                break;
            default:
                column.setCellFactory(param -> new TableCell<>());
                break;
        }

    }

    private ObservableList<Map<String, ConfigValue>> generateDataInMap() {
        ObservableList<Map<String, ConfigValue>> allData = FXCollections.observableArrayList();
        data.forEach( item -> {
            Map<String, ConfigValue> dataRow = new HashMap<>();
            item.entrySet().forEach(entry ->{
                dataRow.put(entry.getKey(), entry.getValue());
            });
            allData.add(dataRow);
        });
        return allData;
    }
    public static void main(String[] args) {
        launch(args);
    }

}
