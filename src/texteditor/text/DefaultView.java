package texteditor.text;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;


public class DefaultView {

    TextArea textArea = new TextArea();

    public DefaultView() {
    }

    public void SetFont(Font font) {
        textArea.setFont(font);
    }

    public Node GetView() {
        return textArea;
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getText() {
        return textArea.getText();
    }
}
