package texteditor.text.custom.canvas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayDeque;

public class CanvasEditor {
    private Canvas canvas;
    private Pane pane;
    private Integer x = 0;
    private ArrayDeque<String> characters;

    public CanvasEditor() {
        this.canvas = new Canvas(400, 200);
        characters = new ArrayDeque<String>();
        canvas.setFocusTraversable(true);
        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Set line width
//        gc.setLineWidth(1.0);
        // Set fill color
//        gc.setFill(Color.BLUE);
        gc.setFont(Font.font("Ubuntu", 10));
        pane = new Pane(); sdk list java
        // Set the Style-properties of the Pane
        pane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");

        // Add the Canvas to the Pane
        pane.getChildren().add(canvas);
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
//                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
//
//                }

                characters.addLast(keyEvent.getText());
                x = x + 7;
                gc.strokeText(keyEvent.getText(), x, 50);
            }
        });
    }

    public Node getView() {
        return pane;
    }

    public String getString() {
        StringBuilder str = new StringBuilder();
        for (var ch :
                characters) {
            str.append(ch);
        }
        return str.toString();
    }
}
