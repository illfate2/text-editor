package texteditor.toolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public interface View {
    public void SetActionOnBold(EventHandler<ActionEvent> handler);

    public void SetActionOnItalic(EventHandler<ActionEvent> handler);

    public boolean IsBoldSelected();

    public boolean IsItalicSelected();

    public String GetFont();

    public Integer GetSize();

    public Node GetView();

    public void SetActionOnSize(EventHandler<ActionEvent> handler);

    public void SetActionOnFont(EventHandler<ActionEvent> handler);
}
