package texteditor.text.custom;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View implements texteditor.text.View {
    HTMLEditor textArea;

    public View() {
        textArea = new HTMLEditor();
        hideHTMLEditorToolbars(textArea);
    }

    private static void hideHTMLEditorToolbars(HTMLEditor editor) {
        editor.setVisible(false);
        Platform.runLater(() -> {
            Node[] nodes = editor.lookupAll(".tool-bar").toArray(new Node[0]);
            for (Node node : nodes) {
                node.setVisible(false);
                node.setManaged(false);
            }
            editor.setVisible(true);
        });
    }

    @Override
    public void SetFont(Font font) {
    }

    @Override
    public Node GetView() {
        return textArea;
    }

    @Override
    public void setText(String text) {
        textArea.setHtmlText(text);
    }

    @Override
    public String getText() {
        return getHTMLText(textArea.getHtmlText());
    }

    public static String getHTMLText(String htmlText) {

        String result = "";

        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlText);
        final StringBuffer text = new StringBuffer(htmlText.length());

        while (matcher.find()) {
            matcher.appendReplacement(
                    text,
                    " ");
        }

        matcher.appendTail(text);

        result = text.toString().trim();

        return result;
    }
}
