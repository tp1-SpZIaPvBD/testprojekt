package sk.timak.testprojekt.domain.searchentity.multipleentities;

import sk.timak.testprojekt.domain.searchentity.fatentity.Labels;

public class Item {
    private String type = "item";
    private String id;
    private Labels labels;

    public Item() {
    }

    public Item(String id, Labels labels) {
        this.id = id;
        this.labels = labels;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }
}
