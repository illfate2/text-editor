package texteditor.text.custom;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Model {
    private String globalFont;
    private Integer globalSize;
    private FontWeight fontWeight;

    public Model(String font, Integer size) {
        globalFont = font;
        globalSize = size;
        this.fontWeight = FontWeight.NORMAL;
    }

    public void setGlobalFont(String globalFont) {
        this.globalFont = globalFont;
    }

    public void setGlobalSize(Integer globalSize) {
        this.globalSize = globalSize;
    }

    public Integer getGlobalSize() {
        return globalSize;
    }

    public String getGlobalFont() {
        return globalFont;
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }
}

