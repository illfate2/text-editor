package texteditor.toolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
        Text text = new Text("    DESIGNED BY A. LEBEDEV");
        text.setStyle(                "    -fx-font: 35px Tahoma;\n" +
                "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                "    -fx-stroke: black;\n" +
                "    -fx-stroke-width: 1;\n");
        Reflection r = new Reflection();
        r.setFraction(0.7f);

        text.setEffect(r);

        sizeComboBox.setStyle("-fx-background-color: rgba(173,255,129,0.5);");
        fontsBox.setStyle("-fx-background-color: rgba(255,34,108,0.5);");
        italic.setStyle("-fx-background-color: rgba(165,76,255,0.5);");
        bolt.setStyle("-fx-background-color: rgba(255,153,44,0.5);");
        panel.getChildren().add(font);
        panel.getChildren().add(size);
        panel.getChildren().add(bolt);
        panel.getChildren().add(italic);
        panel.getChildren().add(text);
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

    public Node GetView() {
        return panel;
    }

    public String GetFont() {
        return fontsBox.getValue();
    }

    public Integer GetSize() {
        return sizeComboBox.getValue();
    }

    public void SetActionOnSize(EventHandler<ActionEvent> handler) {
        sizeComboBox.setOnAction(handler);
    }

    public void SetActionOnFont(EventHandler<ActionEvent> handler) {
        fontsBox.setOnAction(handler);
    }
}
