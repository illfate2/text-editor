package texteditor.options;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashMap;
import java.util.Map;

public class DefaultModel implements Model {
    private String[] options = {"Save", "Open"};

    public DefaultModel() {

    }

    public String[] getOptionNames() {
        return options;
    }

    private void save() {
    }

    private void open() {

    }
}