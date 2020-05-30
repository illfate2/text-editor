package texteditor.text;

import javafx.scene.Node;
import javafx.scene.text.Font;

public interface View {
    public void SetFont(Font font);

    public Node GetView();

    public void setText(String text);


    public void setGText(String text);


    public String getText();

    public String getGText();

}
