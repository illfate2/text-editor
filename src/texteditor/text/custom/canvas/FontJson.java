package texteditor.text.custom.canvas;

import javafx.scene.text.Font;

public class FontJson {
    double size;
    String name;
    String style;
    String family;

    public FontJson(Font font) {
        this.size = font.getSize();
        this.family = font.getFamily();
        this.name = font.getName();
        this.style = font.getStyle();
    }
}
