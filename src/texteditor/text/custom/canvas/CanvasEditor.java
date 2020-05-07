package texteditor.text.custom.canvas;

import com.google.gson.Gson;
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

import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CanvasEditor {
    private Canvas canvas;
    private Pane pane;
    private Integer x = 0;
    private ArrayList<Character> characters;
    private Font globalFont;
    private Character cursor;

    public CanvasEditor(Font defaultFont) {
        this.setGlobalFont(defaultFont);
        this.canvas = new Canvas(800, 400);
        characters = new ArrayList<>();
        canvas.setFocusTraversable(true);
        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
        canvas.setStyle("");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(Font.font("Ubuntu", 14));
        pane = new Pane();
        pane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 0;" +
                "-fx-border-color: #3f1719;");

        pane.getChildren().add(canvas);
        cursor = new Character(true);
        characters.add(cursor);


        int leftSelected, rightSelected;
        ArrayList<Character> selected = new ArrayList<>();
        pane.setOnKeyPressed(keyEvent -> {
            int idx = characters.indexOf(cursor);
            switch (keyEvent.getCode()) {
                case BACK_SPACE:
                    if (idx == 0) {
                        break;
                    }
                    characters.remove(idx - 1);
                    break;
                case ENTER:
                    characters.add(idx, new Character("\n", globalFont));
                    break;
                case LEFT:
                    if (idx == 0) {
                        break;
                    }
                    characters.add(idx - 1, cursor);
                    characters.remove(idx + 1);
                    if (keyEvent.isShiftDown()) {
                        selected.add(characters.get(idx));
                    }
                    break;
                case RIGHT:
                    if (idx > characters.size() - 2) {
                        break;
                    }
                    characters.remove(idx);
                    characters.add(idx + 1, cursor);
                    break;
                case SHIFT:
                case CONTROL:
                case CAPS:
                    break;
                case C:
                    if (keyEvent.isControlDown()) {
                        System.out.println(selected);
                        characters.
                        break;
                    }
                default:
                    characters.add(idx, new Character(keyEvent.getText(), globalFont));
                    break;
            }
            render();
        });

        pane.setOnMouseClicked(mouseEvent -> {
            double curX = mouseEvent.getX();
            double curY = mouseEvent.getY();
            double x = 10;
            double y = 30;
            characters.remove(cursor);
            int idx = 0;
            for (var c :
                    characters) {
                if (c.isEqual("\n")) {
                    y += 20;
                    x = 0;
                } else if (x >= (canvas.getWidth() - c.getSize())) {
                    y += 20;
                    x = 10;
                }
                x = x + c.getSize() / 1.5;
                if (curY <= (y + 10)
                        && curY >= (y - 10)
                        && curX >= (x - c.getSize() / 2)
                        && curX <= (x + c.getSize() / 2)) {
                    characters.add(idx + 1, cursor);
                    render();
                    return;
                }
                idx++;
            }
            characters.add(idx, cursor);
            render();
        });
    }

    public void setGlobalFont(Font globalFont) {
        this.globalFont = globalFont;
    }

    public Node getView() {
        return pane;
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        for (var ch :
                characters) {
            if (ch.isCursor()) {
                continue;
            }
            str.append(ch.getCharacter());
        }
        return str.toString();
    }

    public String getJSONText() {
        Gson gson = new Gson();
        return gson.toJson(characters);
    }

    public void setText(String text) {
        characters.clear();
        for (int i = 0; i < text.length(); i++) {
            String s = String.valueOf(text.charAt(i));
            characters.add(new Character(s, Font.font("ubuntu", 14)));
        }
        render();
    }

    public void setGText(String text) {
        characters.clear();
        var temp = stringToArray(text, Character[].class);
        for (var ch :
                temp) {
            if (ch.isCursor()) {
                continue;
            }
            ch.init();
            characters.add(ch);
        }
        characters.add(cursor);
        render();
    }

    private void render() {
        double x = 10;
        double y = 30;
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var c :
                characters) {
            if (c.isEqual("\n")) {
                y += 20;
                x = 0;
            } else if (x >= (canvas.getWidth() - c.getSize())) {
                y += 20;
                x = 10;
            }

            if (c.isCursor()) {
                var gc = canvas.getGraphicsContext2D();
                gc.setFont(c.getFont());
                canvas.getGraphicsContext2D().fillText("|", x, y);
                continue;
            }
            var gc = canvas.getGraphicsContext2D();
            gc.setFont(c.getFont());
            gc.fillText(c.getCharacter(), x, y);
            x = x + c.getSize() / 1.5;
        }
    }

    public static <T> ArrayList<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return new ArrayList<>(Arrays.asList(arr));
    }
}
