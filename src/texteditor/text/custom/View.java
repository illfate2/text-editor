package texteditor.text.custom;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import texteditor.text.custom.canvas.CanvasEditor;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View implements texteditor.text.View {
    CanvasEditor editor;

    public View() {
        editor = new CanvasEditor();
    }

    @Override
    public void SetFont(Font font) {
    }

    @Override
    public Node GetView() {
        return editor.getView();
    }

    @Override
    public void setText(String text) {
    }

    @Override
    public String getText() {
        return "";
    }


}
