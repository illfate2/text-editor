package texteditor.text.custom;

import javafx.scene.text.Font;

public class Controller implements texteditor.text.Controller {
    private texteditor.text.View view;
    private Model model;

    public Controller(texteditor.text.View view, Model model) {
        this.view = view;
        this.model = model;
        setFont(model.getGlobalFont());
    }

    @Override
    public void setFont(Font font) {
        view.SetFont(font);
        model.setGlobalFont(font);
    }

    @Override
    public void setText(String text) {
        view.setText(text);
    }

    @Override
    public void setGText(String text) {
        view.setGText(text);
    }

    @Override
    public String getText() {
        return view.getText();
    }

    @Override
    public String getGText() {
        return view.getGText();
    }
}
