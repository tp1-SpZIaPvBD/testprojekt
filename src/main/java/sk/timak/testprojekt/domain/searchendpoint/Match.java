package sk.timak.testprojekt.domain.searchendpoint;

public class Match {
    private String type;
    private String language;
    private String text;

    public Match(String text) {
        this.type = "label";
        this.language = "en";
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
