package texteditor.text;

import javafx.scene.text.Font;

public class DefaultController implements Controller {
    View view;

    public DefaultController(View view) {
        this.view = view;
    }

    public void setFont(Font font) {
        view.SetFont(font);
    }

    public void setText(String text) {
        view.setText(text);
    }

    public String getText() {
        return view.getText();
    }
}
