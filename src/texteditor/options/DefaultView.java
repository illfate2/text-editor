package texteditor.options;

import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class DefaultView implements View {
    private MenuButton menuButton;

    public DefaultView() {
        MenuItem menuItem1 = new MenuItem("Option 1");
        MenuItem menuItem2 = new MenuItem("Option 2");
        MenuItem menuItem3 = new MenuItem("Option 3");

        menuButton = new MenuButton("Options", null, menuItem1, menuItem2, menuItem3);
    }

    public Node GetView() {
        return menuButton;
    }
}
