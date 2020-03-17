package texteditor.toolbar;

public interface Model {
    public Integer getSize();

    public String getFont();

    public String[] getFonts();

    public Integer[] getSizes();

    public void setFont(String font);

    public void setSize(Integer size);
}
