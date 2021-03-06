package texteditor.toolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface Controller {
    public void SetActionOnBold(EventHandler<ActionEvent> handler);

    public void SetActionOnItalic(EventHandler<ActionEvent> handler);

    public void SetActionOnSize(EventHandler<ActionEvent> handler);

    public void SetActionOnFont(EventHandler<ActionEvent> handler);
}
