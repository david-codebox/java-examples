package com.sandbox.javafx.controls.tableview.cells;

import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/18 <br>
 * Time: 18:03 <br>
 * </div>
 */
public class ConfigValueCellFactory<T extends ConfigValue> implements Callback<TableColumn<Map<String, ConfigValue>,T>,TableCell<Map<String, ConfigValue>,T>> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Map.Entry<String, ConfigValue> columnData;

    public ConfigValueCellFactory(Map.Entry<String, ConfigValue> columnData) {
        this.columnData = columnData;
    }

    @Override
    public TableCell<Map<String, ConfigValue>, T> call(TableColumn<Map<String, ConfigValue>, T> param) {
        ConfigValue value = columnData.getValue();
        switch (value.valueType()) {
/*            case OBJECT:
                break;
            case LIST:
                break;*/
            case NUMBER:
                Object implValue=value.unwrapped();
                if (implValue instanceof Integer || implValue instanceof Long) {
                    logger.debug("{}.call: Number value type: [{}]", getClass().getName(), implValue.getClass().getName() );
                    return new TextFieldTableCell<>(new ConfigValueStringConverter<>());
                }
                break;
            case BOOLEAN:
                return new CheckBoxTableCell<>(new Callback<Integer, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(Integer p) {
                        BooleanProperty property = new SimpleBooleanProperty((Boolean) param.getCellData(p).unwrapped());
                        property.addListener((observable, oldValue, newValue) -> {
                            param.getTableView().getItems().get(p).put(columnData.getKey(), ConfigValueFactory.fromAnyRef(newValue));
                        });
                        return property;
                    }
                });
            case NULL:
                return new TableCell<>();
            case STRING:
                return new TextFieldTableCell<>(new ConfigValueStringConverter<>());
            default:
                return new TextFieldTableCell<>(new ConfigValueStringConverter<>());
        }

        return new TextFieldTableCell<>(new ConfigValueStringConverter<T>());
    }
}
