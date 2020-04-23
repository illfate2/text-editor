package texteditor;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Controller {
    View view;
    texteditor.toolbar.Controller toolbarController;
    texteditor.text.Controller textController;
    texteditor.options.Controller optionsController;

    // TODO
    public Controller(View view,
                      texteditor.toolbar.Controller toolbarController,
                      texteditor.text.Controller textController,
                      texteditor.options.Controller optionsController) {
        this.view = view;
        toolbarController.SetActionOnItalic(actionEvent -> updateFont());
        toolbarController.SetActionOnBold(actionEvent -> updateFont());
        toolbarController.SetActionOnFont(actionEvent -> updateFont());
        toolbarController.SetActionOnSize(actionEvent -> updateFont());
        this.toolbarController = toolbarController;
        this.textController = textController;


        optionsController.setActionOnOpenOption(actionEvent -> {
            var chooser = view.openFileChooser();
            File selectedFile = chooser.showOpenDialog(null);
            if (selectedFile == null) {
                return;
            }
            StringBuilder resultStringBuilder = new StringBuilder();
            try {
                Scanner myReader = new Scanner(selectedFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    resultStringBuilder.append(data);
                    System.out.println(data);
                }
                myReader.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            textController.setText(resultStringBuilder.toString());
        });

        optionsController.setActionOnSaveOption(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt")
            );
            File selectedFile = fileChooser.showSaveDialog(null);
            saveTextToFile(textController.getText(), selectedFile);
        });

        this.optionsController = optionsController;
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error saving");
        }
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
            textController.setFont(Font.font(view.GetFont(), FontWeight.BOLD, view.GetSize()));
        } else if (!view.IsBoldSelected() && view.IsItalicSelected()) {
            textController.setFont(Font.font(view.GetFont(), FontPosture.ITALIC, view.GetSize()));
        } else if (!view.IsBoldSelected() && !view.IsItalicSelected()) {
            textController.setFont(Font.font(view.GetFont(), FontWeight.NORMAL, view.GetSize()));
        } else if (view.IsBoldSelected() && view.IsItalicSelected()) {
            textController.setFont(Font.font(view.GetFont(), FontWeight.BOLD, FontPosture.ITALIC, view.GetSize()));
        }
    }
}
