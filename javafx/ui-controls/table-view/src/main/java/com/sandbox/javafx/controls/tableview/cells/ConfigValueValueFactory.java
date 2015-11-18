package com.sandbox.javafx.controls.tableview.cells;

import com.typesafe.config.ConfigValue;
import javafx.beans.NamedArg;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.Map;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/18 <br>
 * Time: 17:32 <br>
 * </div>
 */
public class ConfigValueValueFactory<T extends ConfigValue> implements Callback<TableColumn.CellDataFeatures<Map<String, ConfigValue>,ConfigValue>,ObservableValue<T>> {
    private final String key;

    public ConfigValueValueFactory(final @NamedArg("key")String key) {
        this.key = key;
    }

    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<Map<String, ConfigValue>, ConfigValue> cdf) {
        Map<String, ConfigValue> map=cdf.getValue();
        ConfigValue value=map.get(key);
        return new ReadOnlyObjectWrapper<>((T) value);
    }
}
