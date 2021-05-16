package sk.timak.testprojekt.domain.searchentity.fatentity;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class FatEntityObject {
    private Map<String, Object> details = new LinkedHashMap<>();

    public FatEntityObject() {
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    @JsonAnySetter
    public void setDetail(String key, Object value) {
        this.details.put(key, value);
    }
}
