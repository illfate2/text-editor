package texteditor.text.custom;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Model {
    private Font font;

    public Model(String font, Integer size) {
        this.font = Font.font(font, FontWeight.NORMAL, size);
    }

    public void setGlobalFont(Font font) {
        this.font = font;
    }

    public Font getGlobalFont() {
        return font;
    }
}

