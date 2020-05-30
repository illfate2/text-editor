package texteditor.toolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DefaultController implements Controller {
    private Model model;
    private View view;

    public DefaultController(Model model, View view) {
        this.model = model;
        this.view = view;

    }

    public void SetActionOnBold(EventHandler<ActionEvent> handler) {
        view.SetActionOnBold(handler);
    }

    public void SetActionOnItalic(EventHandler<ActionEvent> handler) {
        view.SetActionOnItalic(handler);
    }

    public void SetActionOnSize(EventHandler<ActionEvent> handler) {
        view.SetActionOnSize(handler);
    }

    public void SetActionOnFont(EventHandler<ActionEvent> handler) {
        view.SetActionOnFont(handler);
    }

}
