package com.sandbox.javafx.controls.tableview;

import com.sandbox.javafx.controls.tableview.cells.ConfigValueCellFactory;
import com.sandbox.javafx.controls.tableview.cells.ConfigValueStringConverter;
import com.sandbox.javafx.controls.tableview.cells.ConfigValueValueFactory;
import com.typesafe.config.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private final TableView<Map<String, ConfigValue>> table =new TableView<>();
    private final ObservableList<? extends ConfigObject> data;
    final HBox hb = new HBox();
    final HBox form = new HBox();
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
        table.setItems(generateDataInMap());
        buildColumns(data);

        setUpForm();


        final Button addButton = new Button("Add");
        final Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e ->{
            table.getItems().forEach(map -> {
                logger.debug("ConfigObjectTableView.start: row: [{}]", map );
            });
        });
        addButton.setOnAction((ActionEvent e) -> {
            submitForm();
            resetForm();
        });

//        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.getChildren().addAll(addButton,submitBtn);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb,form);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    private void setUpForm() {
        if (!table.getItems().isEmpty()) {
            table.getItems().stream().findFirst().get().entrySet().forEach( entry -> {
                switch (entry.getValue().valueType()) {
                    case NUMBER:
                        TextField numberField=new TextField();
                        numberField.setId(entry.getKey());
                        form.getChildren().add(numberField);
                        break;
                    case BOOLEAN:
                        CheckBox checkBox = new CheckBox();
                        checkBox.setId(entry.getKey());
                        form.getChildren().add(checkBox);
                        break;
                    case STRING:
                        TextField textField=new TextField();
                        textField.setId(entry.getKey());
                        form.getChildren().add(textField);
                        break;
                    default:
                        TextField defaultField=new TextField();
                        defaultField.setId(entry.getKey());
                        form.getChildren().add(defaultField);
                        break;
                }
            });
        }
    }

    private void submitForm() {
        if (!form.getChildren().isEmpty()) {
            Map<String, ConfigValue> row = new HashMap<>();
            for (Node node : form.getChildren()) {
                if (node instanceof TextField) {
                    TextField textField = (TextField) node;
                    row.put(textField.getId(), ConfigValueFactory.fromAnyRef(textField.getText()));
                } else if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    row.put(checkBox.getId(), ConfigValueFactory.fromAnyRef(checkBox.isSelected()));
                }else if (node instanceof Spinner) {
                    Spinner spinner = (Spinner) node;
                    spinner.getEditor().clear();
                    row.put(spinner.getId(), ConfigValueFactory.fromAnyRef(spinner.getValue()));
                } else {
                    logger.error("ConfigObjectTableView.resetForm: Unsupported control [{}]",node.getClass().getName() );
                }

            }
            table.getItems().add(row);
        }
    }
    private void resetForm() {
        if (!form.getChildren().isEmpty()) {
            for (Node node : form.getChildren()) {
                if (node instanceof TextField) {
                    TextField textField = (TextField) node;
                    textField.clear();
                } else if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    checkBox.setSelected(false);
                }else if (node instanceof Spinner) {
                    Spinner spinner = (Spinner) node;
                    spinner.getEditor().clear();
                } else {
                    logger.error("ConfigObjectTableView.resetForm: Unsupported control [{}]",node.getClass().getName() );
                }

            }
        }
    }

    private Map<String, ConfigValue> createNewRow(ConfigObject template) {
        Map<String, ConfigValue> row = new HashMap<>();
        template.entrySet().forEach(entry -> {
            switch (entry.getValue().valueType()) {
                case NUMBER:
                    row.put(entry.getKey(), ConfigValueFactory.fromAnyRef(0));
                    break;
                case BOOLEAN:
                    row.put(entry.getKey(), ConfigValueFactory.fromAnyRef(false));
                    break;
                case STRING:
                    row.put(entry.getKey(), ConfigValueFactory.fromAnyRef(""));
                    break;
                default:
                    row.put(entry.getKey(), ConfigValueFactory.fromAnyRef(""));
                    break;
            }
        });
        return row;
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
//            table.getColumns().sort((o1, o2) -> o1.getText().compareTo(o2.getText()));
        });
    }

    private void setUpCellFactory(TableColumn<Map<String, ConfigValue>, ConfigValue> column, Map.Entry<String, ConfigValue> entry) {

        switch (entry.getValue().valueType()) {
            case STRING:
                column.setCellFactory(TextFieldTableCell.forTableColumn(new ConfigValueStringConverter<>()));
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
