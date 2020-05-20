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
    private View view;
    private texteditor.text.Controller textController;

    public Controller(View view,
                      texteditor.toolbar.Controller toolbarController,
                      texteditor.text.Controller textController,
                      texteditor.options.Controller optionsController) {
        this.view = view;
        toolbarController.SetActionOnItalic(actionEvent -> updateFont());
        toolbarController.SetActionOnBold(actionEvent -> updateFont());
        toolbarController.SetActionOnFont(actionEvent -> updateFont());
        toolbarController.SetActionOnSize(actionEvent -> updateFont());
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
                }
                myReader.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            var filter = chooser.getSelectedExtensionFilter();
            if (filter.getDescription().equals("Text Files")) {
                textController.setText(resultStringBuilder.toString());
                return;
            }
            if (filter.getDescription().equals("G Format")) {
                textController.setGText(resultStringBuilder.toString());
            }
        });

        optionsController.setActionOnSaveOption(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("G Format", "*.gtxt")
            );
            File selectedFile = fileChooser.showSaveDialog(null);
            var filter = fileChooser.getSelectedExtensionFilter();
            if (filter.getDescription().equals("Text Files")) {
                saveTextToFile(textController.getText(), selectedFile);
                return;
            }
            if (filter.getDescription().equals("G Format")) {
                saveTextToFile(textController.getGText(), selectedFile);
            }
        });

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
        primaryStage.setScene(new Scene(root, 850, 500));
        primaryStage.show();
    }

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
