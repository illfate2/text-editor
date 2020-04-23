package texteditor.options;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

public class DefaultController implements Controller {
    Model model;
    View view;

    public DefaultController(Model model, View view) {
        this.view = view;
        this.model = model;
    }

    public void setActionOnOpenOption(EventHandler<ActionEvent> action) {
        view.setActionOnMenuButton("Open", action);
    }

    public void setActionOnSaveOption(EventHandler<ActionEvent> action) {
        view.setActionOnMenuButton("Save", action);
    }

}
