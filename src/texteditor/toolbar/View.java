package texteditor.toolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface View {
    public void SetActionOnBold(EventHandler<ActionEvent> handler);

    public void SetActionOnItalic(EventHandler<ActionEvent> handler);

    public boolean IsBoldSelected();

    public boolean IsItalicSelected();
}
