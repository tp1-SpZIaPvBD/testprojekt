package sk.timak.testprojekt.domain.searchentity.multipleentities;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonPropertyOrder({ "entities", "id" })
public class MultipleEntities {
    private Map<String, Object> entities = new LinkedHashMap<>();
    private Integer success = 1;

    public MultipleEntities() {
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
