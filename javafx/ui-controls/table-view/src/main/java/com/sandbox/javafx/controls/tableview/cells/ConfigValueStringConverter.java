package com.sandbox.javafx.controls.tableview.cells;

import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;
import javafx.util.StringConverter;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/18 <br>
 * Time: 17:55 <br>
 * </div>
 */
public class ConfigValueStringConverter<T extends ConfigValue> extends StringConverter<T> {
    @Override
    public String toString(T value) {
        return value.unwrapped().toString();
    }

    @Override
    public T fromString(String value) {
        return (T) ConfigValueFactory.fromAnyRef(value);
    }
}
