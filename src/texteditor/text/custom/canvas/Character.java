package texteditor.text.custom.canvas;

import com.google.gson.Gson;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Character {
    private String character;
    private transient Font font;
    private transient boolean isSelected;
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
        String[] splited = fontJson.style.split("\\s+");
        if (splited.length == 2) {
            font = Font.font(fontJson.family, FontWeight.BOLD, FontPosture.ITALIC, fontJson.size);
        }
        if (splited.length == 1) {
            if (splited[0].equals("Bold")) {
                font = Font.font(fontJson.family, FontWeight.BOLD, fontJson.size);
            } else if (splited[0].equals("Italic")) {
                font = Font.font(fontJson.family, FontPosture.ITALIC, fontJson.size);
            } else {
                font = Font.font(fontJson.family, fontJson.size);
            }
        }
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

    public void setFont(Font font) {
        this.font = font;
        fontJson = new FontJson(font);
    }

    public Font getFont() {
        return font;
    }

    public boolean isCursor() {
        return isCursor;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
