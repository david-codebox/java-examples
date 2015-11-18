package com.sandbox.javafx.controls.tableview;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/18 <br>
 * Time: 9:24 <br>
 * </div>
 */

import com.sandbox.javafx.controls.tableview.model.Person;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A simple table that uses cell factories to add a control to a table
 * <p>
 * column and to enable editing of first/last name and email.
 *
 * @see javafx.scene.control.TableCell
 * @see javafx.scene.control.TableColumn
 * @see javafx.scene.control.TablePosition
 * @see javafx.scene.control.TableRow
 * @see javafx.scene.control.TableView
 */

public class TableViewCheckboxDemo extends Application {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private void init(Stage primaryStage) {

        Group root = new Group();

        primaryStage.setScene(new Scene(root));

        final ObservableList<Person> data = FXCollections.observableArrayList(

                new Person(true, "Jacob", "Smith", "jacob.smith@example.com"),

                new Person(false, "Isabella", "Johnson", "isabella.johnson@example.com"),

                new Person(true, "Ethan", "Williams", "ethan.williams@example.com"),

                new Person(true, "Emma", "Jones", "emma.jones@example.com"),

                new Person(true, "Jacob", "Smith", "jacob.smith@example.com"),

                new Person(false, "Isabella", "Johnson", "isabella.johnson@example.com"),

                new Person(true, "Ethan", "Williams", "ethan.williams@example.com"),

                new Person(true, "Jacob", "Smith", "jacob.smith@example.com"),

                new Person(false, "Isabella", "Johnson", "isabella.johnson@example.com"),

                new Person(true, "Ethan", "Williams", "ethan.williams@example.com"),

                new Person(true, "Emma", "Jones", "emma.jones@example.com"),

                new Person(true, "Jacob", "Smith", "jacob.smith@example.com"),

                new Person(false, "Isabella", "Johnson", "isabella.johnson@example.com"),

                new Person(true, "Ethan", "Williams", "ethan.williams@example.com"),

                new Person(true, "Emma", "Jones", "emma.jones@example.com"),

                new Person(true, "Jacob", "Smith", "jacob.smith@example.com"),

                new Person(false, "Isabella", "Johnson", "isabella.johnson@example.com"),

                new Person(true, "Ethan", "Williams", "ethan.williams@example.com"),

                new Person(true, "Emma", "Jones", "emma.jones@example.com"),

                new Person(false, "Michael", "Brown", "michael.brown@example.com"));

        //"Invited" column
        TableColumn<Person, Boolean> invitedCol = new TableColumn<>();
        invitedCol.setText("Invited");
        invitedCol.setMinWidth(50);
        invitedCol.setCellValueFactory(new PropertyValueFactory<>("invited"));
        invitedCol.setCellFactory(p -> new CheckBoxTableCell<>());

        //"First Name" column
        TableColumn<Person, String> firstNameCol = new TableColumn<>();
        firstNameCol.setText("First");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //"Last Name" column
        TableColumn<Person, String> lastNameCol = new TableColumn<>();
        lastNameCol.setText("Last");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        //"Email" column

        TableColumn<Person, String> emailCol = new TableColumn<>();
        emailCol.setText("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        //Set cell factory for cells that allow editing
/*        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory =
                p -> new EditingCell();
        emailCol.setCellFactory(cellFactory);
        firstNameCol.setCellFactory(cellFactory);
        lastNameCol.setCellFactory(cellFactory);*/
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());


        //Set handler to update ObservableList properties. Applicable if cell is edited

        updateObservableListProperties(emailCol, firstNameCol, lastNameCol);

        TableView<Person> tableView = new TableView<>();

        tableView.setItems(data);

        //Enabling editing

        VBox vb = new VBox();

        tableView.setEditable(true);

        tableView.getColumns().addAll(invitedCol, firstNameCol, lastNameCol, emailCol);


        CheckBox cb = new CheckBox("Select all");
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            data.forEach(item -> {
                logger.debug("TableViewCheckboxDemo.init: [{}]", item);
            });
        });
        cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                if (new_val) {
                    for (Person p : data) {
                        //p.invited.set(true);
                    }

                }


            }
        });

        HBox hBox = new HBox(15);
        hBox.getChildren().addAll(cb, submitButton);
        vb.setSpacing(20);
        vb.getChildren().addAll(hBox,tableView);
        root.getChildren().addAll(vb);

    }


    private void updateObservableListProperties(TableColumn emailCol, TableColumn firstNameCol,

                                                TableColumn lastNameCol) {

        //Modifying the email property in the ObservableList

        emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {

            @Override
            public void handle(CellEditEvent<Person, String> t) {

                ((Person) t.getTableView().getItems().get(

                        t.getTablePosition().getRow())).setEmail(t.getNewValue());

            }

        });

        //Modifying the firstName property in the ObservableList

        firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {

            @Override
            public void handle(CellEditEvent<Person, String> t) {

                ((Person) t.getTableView().getItems().get(

                        t.getTablePosition().getRow())).setFirstName(t.getNewValue());

            }

        });

        //Modifying the lastName property in the ObservableList

        lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {

            @Override
            public void handle(CellEditEvent<Person, String> t) {

                ((Person) t.getTableView().getItems().get(

                        t.getTablePosition().getRow())).setLastName(t.getNewValue());

            }

        });

    }

    // EditingCell - for editing capability in a TableCell
    public static class EditingCell extends TableCell<Person, String> {

        private TextField textField;


        public EditingCell() {

        }


        @Override
        public void startEdit() {

            super.startEdit();


            if (textField == null) {

                createTextField();

            }

            setText(null);

            setGraphic(textField);

            textField.selectAll();

        }


        @Override
        public void cancelEdit() {

            super.cancelEdit();

            setText((String) getItem());

            setGraphic(null);

        }


        @Override
        public void updateItem(String item, boolean empty) {

            super.updateItem(item, empty);

            if (empty) {

                setText(null);

                setGraphic(null);

            } else {

                if (isEditing()) {

                    if (textField != null) {

                        textField.setText(getString());

                    }

                    setText(null);

                    setGraphic(textField);

                } else {

                    setText(getString());

                    setGraphic(null);

                }

            }

        }


        private void createTextField() {

            textField = new TextField(getString());

            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);

            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {

                    if (t.getCode() == KeyCode.ENTER) {

                        commitEdit(textField.getText());

                    } else if (t.getCode() == KeyCode.ESCAPE) {

                        cancelEdit();

                    }

                }

            });

        }


        private String getString() {

            return getItem() == null ? "" : getItem().toString();

        }

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        init(primaryStage);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

