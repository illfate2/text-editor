package texteditor.text.custom;

import javafx.scene.text.Font;

public class Controller implements texteditor.text.Controller {
    texteditor.text.View view;
    Model model;

    public Controller(texteditor.text.View view, Model model) {
        this.view = view;
        this.model = model;
        setFont(model.getGlobalFont());
    }

    @Override
    public void setFont(Font font) {
        model.setGlobalFont(font);
    }

    public void setFont(Font font, int from, int to) {
    }


    @Override
    public void setText(String text) {
        view.setText(text);
    }

    @Override
    public String getText() {
        return view.getText();
    }
}
