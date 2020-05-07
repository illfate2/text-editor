package texteditor.options;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultView implements View {
    private MenuButton menuButton;
    private Map<String, MenuItem> optionsButtons;

    public DefaultView(String[] options) {
        MenuItem[] items = new MenuItem[options.length];
        optionsButtons = new HashMap<>();
        int counter = 0;
        for (String name : options) {
            MenuItem item = new MenuItem(name);
            optionsButtons.put(name, item);
            items[counter++] = item;
        }
        menuButton = new MenuButton("Options", null, items);
        menuButton.setStyle("-fx-background-color: rgba(187,89,255,0.5);");
    }

    public Node GetView() {
        return menuButton;
    }

    public void setActionOnMenuButton(String name, EventHandler<ActionEvent> action) {
        optionsButtons.get(name).setOnAction(action);
    }

    public void openFile(){

    }

}
