package sk.timak.testprojekt.domain.searchentity.fatentity;

import sk.timak.testprojekt.domain.searchentity.simple.Entity;

public class FatEntity {
    private FatEntityObject entities;
    private Integer success = 1;

    public FatEntity() {
    }

    public FatEntityObject getEntities() {
        return entities;
    }

    public void setEntities(FatEntityObject entities) {
        this.entities = entities;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
