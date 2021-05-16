package sk.timak.testprojekt.domain.searchentity.simple;

public class SimpleEntity {
    private Entity entities;
    private Integer success = 1;

    public SimpleEntity() {
    }

    public Entity getEntities() {
        return entities;
    }

    public void setEntities(Entity entities) {
        this.entities = entities;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
