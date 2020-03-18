package texteditor.text;

import javafx.scene.Node;
import javafx.scene.text.Font;

public interface View {
    public void SetFont(Font font);

    public Node GetView();
}
