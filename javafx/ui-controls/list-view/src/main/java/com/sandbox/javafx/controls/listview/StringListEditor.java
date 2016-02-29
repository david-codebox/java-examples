package com.sandbox.javafx.controls.listview;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/12/8 <br>
 * Time: 15:23 <br>
 * </div>
 */

public class StringListEditor extends VBox {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ListView<String> listView;
    private ObservableList<String> data;
    private Button addBtn;
    private Button removeBtn;
    private TextField textField = new TextField();
    private final HBox form = new HBox(5);

    public StringListEditor(ObservableList<String> data) {
        this.data = data;
        if (data != null) {
            listView = new ListView<>(data);
        } else {
            listView = new ListView<>();
        }
    }

    public ObservableList<String> getData() {
        return listView.getItems();
    }

    private void initForm() {
        form.getChildren().clear();

        form.getChildren().add(textField);

        addBtn = GlyphsDude.createIconButton(MaterialDesignIcon.PLUS,"4em");
        removeBtn = GlyphsDude.createIconButton(MaterialDesignIcon.MINUS,"4em");
    }
}
