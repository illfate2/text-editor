package texteditor.toolbar;

public class DefaultModel implements Model {
    private boolean isBold;
    private boolean isItalic;

    private String[] fonts = {"Ubuntu", "Waree", "Chandas", "Source Code Pro"};
    private Integer[] sizes = {10, 14, 18, 22};

    private Integer size = 14;
    private String font = "Times New Roman";

    public Integer getSize() {
        return size;
    }

    public String getFont() {
        return font;
    }

    public String[] getFonts() {
        return fonts;
    }

    public Integer[] getSizes() {
        return sizes;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
