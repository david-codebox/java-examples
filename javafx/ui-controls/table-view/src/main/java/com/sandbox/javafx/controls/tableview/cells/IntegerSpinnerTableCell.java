package com.sandbox.javafx.controls.tableview.cells;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/16 <br>
 * Time: 12:32 <br>
 * </div>
 */
public class IntegerSpinnerTableCell<S,T extends Number> extends TableCell<S,T> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Spinner<T> spinner;
    private ObjectProperty<StringConverter<T>> converter =
            new SimpleObjectProperty<>(this, "converter");

/*    @SuppressWarnings("unchecked")
    public IntegerSpinnerTableCell(int value) {
        super();
        this.value.set(value);
        spinner = new Spinner<>();
        spinner.setValueFactory((SpinnerValueFactory<T>) new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE,value));
        setConverter(new IntegerStringConverter());
    }*/

    @SuppressWarnings("unchecked")
    public IntegerSpinnerTableCell() {
        super();
        setConverter((StringConverter<T>) new IntegerStringConverter());

    }
    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    public StringConverter getConverter() {
        return converter.get();
    }

    public ObjectProperty<StringConverter<T>> converterProperty() {
        return converter;
    }

    public void setConverter(StringConverter<T> converter) {
        this.converter.set(converter);
    }

    /***************************************************************************
     *                                                                         *
     * Public API                                                              *
     *                                                                         *
     **************************************************************************/

    @Override
    public void startEdit() {
        if (! isEditable() || ! getTableView().isEditable() || ! getTableColumn().isEditable()) {
            return;
        }
        if (spinner == null) {
            logger.debug("IntegerSpinnerTableCell.startEdit: creating spinner....");
            spinner = createSpinner();
        }
        logger.debug("IntegerSpinnerTableCell.startEdit: spinner value: [{}]", spinner.getValue() );


        logger.debug("IntegerSpinnerTableCell.startEdit: start editing...");
        super.startEdit();
        setText(null);
        setGraphic(spinner);
    }


    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(converter.get().toString(item));
            setGraphic(null);
            ObservableValue<T> ov=(ObservableValue<T>) getTableColumn().getCellObservableValue(getIndex());
            if (spinner != null) {
                spinner.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (!ov.getValue().equals(getItem())) {
//                            commitEdit(getItem());
                        }
                        commitEdit(spinner.getValue());
                    }
                });
//                logger.debug("IntegerSpinnerTableCell.updateItem: spinner value [{}]", spinner.getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getConverter().toString(getItem()));
        setGraphic(null);
    }

    @SuppressWarnings("unchecked")
    private Spinner<T> createSpinner() {
        T item = getItem();
        logger.debug("IntegerSpinnerTableCell.createSpinner: item type: [{}]", item.getClass().getName());
        logger.debug("IntegerSpinnerTableCell.createSpinner: item value: [{}]", item);
        SpinnerValueFactory<T> valueFactory=(SpinnerValueFactory<T>) new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, item.intValue());
        if (item instanceof Double ) {
            valueFactory = (SpinnerValueFactory<T>) new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, item.doubleValue());
        } else if(!(item instanceof Integer)) {
            logger.error("IntegerSpinnerTableCell.createSpinner: Unsupported value type: [{}]", item.getClass().getName() );
        }
        valueFactory.valueProperty().bindBidirectional(itemProperty());
        return new Spinner<>(valueFactory);
    }
}
