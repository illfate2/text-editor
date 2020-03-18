package texteditor;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import texteditor.text.DefaultController;

public class Controller {
    View view;
    texteditor.toolbar.Controller toolbarController;
    DefaultController textController;

    // TODO
    public Controller(texteditor.toolbar.View toolbarView, texteditor.text.View textView,
                      texteditor.toolbar.Controller toolbarController, DefaultController textController) {
        view = new View(toolbarView, textView);
        toolbarController.SetActionOnItalic(actionEvent -> updateFont());
        toolbarController.SetActionOnBold(actionEvent -> updateFont());
        toolbarController.SetActionOnFont(actionEvent -> updateFont());
        toolbarController.SetActionOnSize(actionEvent -> updateFont());
        this.toolbarController = toolbarController;
        this.textController = textController;
    }

    public void Launch(Stage primaryStage) {
        Group root = new Group();
        root.getChildren().add(view.Get());

        primaryStage.setTitle("Text editor");
        primaryStage.setScene(new Scene(root, 350, 275));
        primaryStage.show();
    }

    // TODO normal builder
    private void updateFont() {
        System.out.println(view.GetFont());
        if (view.IsBoldSelected() && !view.IsItalicSelected()) {
            textController.SetFont(Font.font(view.GetFont(), FontWeight.BOLD, view.GetSize()));
        } else if (!view.IsBoldSelected() && view.IsItalicSelected()) {
            textController.SetFont(Font.font(view.GetFont(), FontPosture.ITALIC, view.GetSize()));
        } else if (!view.IsBoldSelected() && !view.IsItalicSelected()) {
            textController.SetFont(Font.font(view.GetFont(), FontWeight.NORMAL, view.GetSize()));
        } else if (view.IsBoldSelected() && view.IsItalicSelected()) {
            textController.SetFont(Font.font(view.GetFont(), FontWeight.BOLD, FontPosture.ITALIC, view.GetSize()));
        }
    }
}
