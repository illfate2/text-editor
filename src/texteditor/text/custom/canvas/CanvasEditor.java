package texteditor.text.custom.canvas;

import com.google.gson.Gson;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class CanvasEditor {
    private Canvas canvas;
    private Pane pane;
    private Integer x = 0;
    private ArrayList<Character> characters;
    private ArrayList<Character> buffer;
    private Font globalFont;
    private Character cursor;
    private AtomicInteger leftSelected;
    private AtomicInteger rightSelected;

    public CanvasEditor(Font defaultFont) {
        this.setGlobalFont(defaultFont);
        this.canvas = new Canvas(800, 800);
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
                "-fx-border-color: #3f370b;");

        pane.getChildren().add(canvas);
        cursor = new Character(true);
        characters.add(cursor);
        buffer = new ArrayList<>();

        leftSelected = new AtomicInteger(-1);
        rightSelected = new AtomicInteger(-1);
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
                        leftSelected.set(idx);
                        if (rightSelected.get() == -1) {
                            rightSelected.set(leftSelected.get());
                        }
                        break;
                    }
                    leftSelected.set(-1);
                    rightSelected.set(-1);
                    break;
                case RIGHT:
                    if (idx > characters.size() - 2) {
                        break;
                    }
                    characters.remove(idx);
                    characters.add(idx + 1, cursor);
                    leftSelected.set(-1);
                    rightSelected.set(-1);
                    break;
                case SHIFT:
                case CONTROL:
                case CAPS:
                    break;
                case C:
                    if (keyEvent.isControlDown()) {
                        buffer.clear();
                        for (int i = leftSelected.get(); i <= rightSelected.get(); ++i) {
                            buffer.add(characters.get(i));
                        }
                        break;
                    }
                    characters.add(idx, new Character(keyEvent.getText(), globalFont));
                    leftSelected.set(-1);
                    rightSelected.set(-1);
                    break;
                case V:
                    if (keyEvent.isControlDown()) {
                        characters.addAll(characters.indexOf(cursor), buffer);
                        break;
                    }
                default:
                    characters.add(idx, new Character(keyEvent.getText(), globalFont));
                    leftSelected.set(-1);
                    rightSelected.set(-1);
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
        if (leftSelected != null && leftSelected.get() != -1 && rightSelected.get() != 1) {
            for (int i = leftSelected.get(); i <= rightSelected.get(); ++i) {
                characters.get(i).setFont(globalFont);
            }
            render();
        }
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
            int idx = characters.indexOf(c);
            if (idx >= leftSelected.get() && idx <= rightSelected.get()) {
                gc.setFill(Color.RED);
                gc.fillText(c.getCharacter(), x, y);
                x = x + c.getSize() / 1.5;
                continue;
            }
            gc.setFill(Color.BLACK);
            gc.fillText(c.getCharacter(), x, y);
            x = x + c.getSize() / 1.5;
        }
    }

    public static <T> ArrayList<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return new ArrayList<>(Arrays.asList(arr));
    }
}
