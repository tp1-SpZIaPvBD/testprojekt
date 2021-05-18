package sk.timak.testprojekt.domain.searchentity.fatentity.claims;

public class DataValue {
    private String value;
    private String type;

    public DataValue() {
    }

    public DataValue(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
