package osmedile.intellij.stringmanip.sort.support;

public class SimpleSortable implements Sortable {
    private final String textForComparison;
    private String token;

    public SimpleSortable(String token, String textForComparison) {
        this.token = token;
        this.textForComparison = textForComparison;
    }

    @Override
    public String getTextForComparison() {
        return textForComparison;
    }

    @Override
    public String getText() {
        return token;
    }

    @Override
    public String toString() {
        return "SimpleSortable{" +
                "token='" + token + '\'' +
                ", textForComparison='" + textForComparison + '\'' +
                '}';
    }
}
