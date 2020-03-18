package texteditor.text;

import javafx.scene.text.Font;

public class DefaultController {
    View view;

    public DefaultController(View view) {
        this.view = view;
    }

    public void SetFont(Font font) {
        view.SetFont(font);
    }
}
