package texteditor;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // TODO factory
        texteditor.toolbar.Model toolbarModel = new texteditor.toolbar.DefaultModel();
        texteditor.toolbar.View toolbarView = new texteditor.toolbar.DefaultView(toolbarModel.getFonts(), toolbarModel.getSizes(), toolbarModel.getSize());
        texteditor.toolbar.Controller toolbarController = new texteditor.toolbar.DefaultController(toolbarModel, toolbarView);

        texteditor.text.View textView = new texteditor.text.custom.View();
        texteditor.text.custom.Model textModel = new texteditor.text.custom.Model(toolbarModel.getFont(), toolbarModel.getSize());
        texteditor.text.Controller textController = new texteditor.text.custom.Controller(textView, textModel);
        texteditor.options.Model optionsModel = new texteditor.options.DefaultModel();
        texteditor.options.View optionsView = new texteditor.options.DefaultView(optionsModel.getOptionNames());
        texteditor.options.Controller optionsController = new texteditor.options.DefaultController(optionsModel, optionsView);
        View view = new View(toolbarView, textView, optionsView);
        Controller controller = new Controller(view, toolbarController, textController, optionsController);

        controller.Launch(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
