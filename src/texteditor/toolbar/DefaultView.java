package texteditor.toolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DefaultView implements View {
    private HBox panel = new HBox();
    CheckBox bolt = new CheckBox("bold");
    CheckBox italic = new CheckBox("italic");
    ComboBox<Integer> sizeComboBox = new ComboBox<>();
    ComboBox<String> fontsBox = new ComboBox<>();
    VBox font = new VBox();

    public DefaultView(String[] fonts, Integer[] sizes, Integer defaultSize) {
        fontsBox.getItems().addAll(fonts);
        fontsBox.getSelectionModel().selectFirst();
        font.getChildren().add(fontsBox);

        VBox size = new VBox();
        sizeComboBox.getItems().addAll(sizes);
        sizeComboBox.getSelectionModel().select(defaultSize);
        size.getChildren().add(sizeComboBox);


        panel.getChildren().add(font);
        panel.getChildren().add(size);
        panel.getChildren().add(bolt);
        panel.getChildren().add(italic);
    }

    public void SetActionOnBold(EventHandler<ActionEvent> handler) {
        bolt.setOnAction(handler);
    }

    public void SetActionOnItalic(EventHandler<ActionEvent> handler) {
        italic.setOnAction(handler);
    }

    public boolean IsBoldSelected() {
        return bolt.isSelected();
    }

    public boolean IsItalicSelected() {
        return italic.isSelected();
    }
}
