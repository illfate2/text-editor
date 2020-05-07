package texteditor.text.custom;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import texteditor.text.custom.canvas.CanvasEditor;

public class View implements texteditor.text.View {
    CanvasEditor editor;
    ScrollPane scrollPane;

    public View(Font font) {

        editor = new CanvasEditor(font);
        scrollPane = new ScrollPane(editor.getView());
        scrollPane.setPrefViewportHeight(400);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    @Override
    public void SetFont(Font font) {
        editor.setGlobalFont(font);
    }

    @Override
    public Node GetView() {
        return scrollPane;
    }

    @Override
    public void setText(String text) {
        editor.setText(text);
    }

    @Override
    public void setGText(String text) {
        editor.setGText(text);
    }

    @Override
    public String getText() {
        return editor.getText();
    }

    @Override
    public String getGText() {
        return editor.getJSONText();
    }
}
