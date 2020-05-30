package texteditor.options;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface Controller {
    public void setActionOnOpenOption(EventHandler<ActionEvent> action);
    public void setActionOnSaveOption(EventHandler<ActionEvent> action);

}
