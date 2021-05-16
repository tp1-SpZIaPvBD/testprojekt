package sk.timak.testprojekt.domain.searchentity.missing;

public class MissingEntity {
    private String id;
    private String missing = "";

    public MissingEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMissing() {
        return missing;
    }

    public void setMissing(String missing) {
        this.missing = missing;
    }
}
