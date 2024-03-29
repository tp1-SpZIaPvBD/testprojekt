package sk.timak.testprojekt.domain.searchentity.fatentity;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class FatEntity {
    private Map<String, Object> entities = new LinkedHashMap<>();
    private Integer success = 1;

    public FatEntity() {
    }

    public Map<String, Object> getEntities() {
        return entities;
    }

    @JsonAnySetter
    public void setEntities(String key, Object value) {
        this.entities.put(key, value);
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
