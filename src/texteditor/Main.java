package texteditor;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // TODO
        texteditor.toolbar.Model toolbarModel = new texteditor.toolbar.DefaultModel();
        texteditor.toolbar.View toolbarView = new texteditor.toolbar.DefaultView(toolbarModel.getFonts(), toolbarModel.getSizes(), toolbarModel.getSize());
        texteditor.toolbar.Controller toolbarController = new texteditor.toolbar.DefaultController(toolbarModel, toolbarView);

        texteditor.text.View textView = new texteditor.text.DefaultView();
        texteditor.text.DefaultController textController = new texteditor.text.DefaultController(textView);
        texteditor.options.View optionsView = new texteditor.options.DefaultView();
        View view = new View(toolbarView, textView, optionsView);
        Controller controller = new Controller(view, toolbarController, textController);

        controller.Launch(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
