package com.sandbox.javafx.controlsfx;

import com.sandbox.javafx.controls.core.BaseApplication;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;
import sun.util.resources.cldr.sah.LocaleNames_sah;

import java.util.Locale;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2016/2/23 <br>
 * Time: 15:54 <br>
 * </div>
 */

public class CheckComboBoxTest extends BaseApplication {
    @Override
    protected void doInitialization() throws Exception {
        ObservableList<Locale> locales = FXCollections.observableArrayList(Locale.getAvailableLocales());
        CheckComboBox<Locale> localesBox = new CheckComboBox<>(locales);
        localesBox.setConverter(new StringConverter<Locale>() {
            @Override
            public String toString(Locale object) {
                logger.debug("CheckComboBoxTest.toString: converting [{}]", object.toLanguageTag() );
                return object.getDisplayName(Locale.ENGLISH);
            }

            @Override
            public Locale fromString(String string) {
                logger.debug("CheckComboBoxTest.toString: converting to locale: [{}]", string );
                return Locale.forLanguageTag(string);
            }
        });

        localesBox.getCheckModel().check(Locale.SIMPLIFIED_CHINESE);
        localesBox.getCheckModel().check(Locale.GERMAN);

        localesBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<Locale>() {
            @Override
            public void onChanged(Change<? extends Locale> c) {
                logger.debug("CheckComboBoxTest.onChanged: [{}]", c.getList() );
                logger.debug("CheckComboBoxTest.onChanged: [{}]", localesBox.getCheckModel().getCheckedItems());
            }
        });
        addNode(localesBox);
    }
}
