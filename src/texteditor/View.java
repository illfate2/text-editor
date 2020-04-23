package texteditor;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class View {
    private VBox vBox = new VBox();
    private texteditor.toolbar.View toolbarView;
    private texteditor.text.View textView;
    private texteditor.options.View optionsView;

    public View(texteditor.toolbar.View toolbarView, texteditor.text.View textView, texteditor.options.View optionsView) {
        this.toolbarView = toolbarView;
        this.textView = textView;
        this.optionsView = optionsView;
        vBox.getChildren().addAll(optionsView.GetView(), toolbarView.GetView(), textView.GetView());
    }

    public boolean IsBoldSelected() {
        return toolbarView.IsBoldSelected();
    }

    public boolean IsItalicSelected() {
        return toolbarView.IsItalicSelected();
    }

    public Node Get() {
        return vBox;
    }

    public String GetFont() {
        return toolbarView.GetFont();
    }

    public Integer GetSize() {
        return toolbarView.GetSize();
    }

    public FileChooser openFileChooser() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        return fileChooser;
    }
}
