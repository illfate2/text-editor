package texteditor.text.custom.canvas;

import com.google.gson.Gson;
import javafx.scene.text.Font;

public class Character {
    private String character;
    private transient Font font;
    private FontJson fontJson;
    private boolean isCursor;

    public Character(String character, Font font) {
        this.character = character;
        this.font = font;
        fontJson = new FontJson(font);
    }

    public Character(boolean isCursor) {
        this.isCursor = isCursor;
    }

    public void init() {
        font = new Font(fontJson.name, fontJson.size);
    }

    public double getSize() {
        if (font == null) {
            return 0;
        }
        return font.getSize();
    }

    public String getCharacter() {
        return character;
    }

    public boolean isEqual(String ch) {
        if (character != null) {
            return character.equals(ch);
        }
        return false;
    }


    public Font getFont() {
        return font;
    }

    public boolean isCursor() {
        return isCursor;
    }

}
