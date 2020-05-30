package texteditor.options;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public interface View {
    public Node GetView();

    public void setActionOnMenuButton(String name, EventHandler<ActionEvent> action);

}
