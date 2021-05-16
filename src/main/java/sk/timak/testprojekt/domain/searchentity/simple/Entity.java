package sk.timak.testprojekt.domain.searchentity.simple;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Entity {
    private Map<String, Object> details = new LinkedHashMap<>();

    public Entity() {
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    @JsonAnySetter
    public void setDetail(String key, Object value) {
        this.details.put(key, value);
    }
}
