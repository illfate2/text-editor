package texteditor.text;

import javafx.scene.text.Font;

public interface Controller {
    public void setFont(Font font);

    public void setText(String text);

    public String getText();
}
