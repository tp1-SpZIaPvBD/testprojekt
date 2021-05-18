package sk.timak.testprojekt.domain.searchentity.fatentity;

public class En {
    private String language = "en";
    private String value;

    public En() {
    }

    public En(String value) {
        this.value = value;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
