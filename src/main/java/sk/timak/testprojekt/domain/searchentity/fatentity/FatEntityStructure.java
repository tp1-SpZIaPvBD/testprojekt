package sk.timak.testprojekt.domain.searchentity.fatentity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

public class FatEntityStructure {
    private String type = "item";
    private String id;
    private Labels labels;
    private Descriptions descriptions;
    private Map<String, Object> claims = new LinkedHashMap<>();
    @JsonProperty("aliases")
    private Aliases aliases = new Aliases();

    public FatEntityStructure() {
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

    public Descriptions getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Descriptions descriptions) {
        this.descriptions = descriptions;
    }

    public Map<String, Object> getClaims() {
        return claims;
    }

    @JsonAnySetter
    public void setClaims(String key, Object value) {
        this.claims.put(key, value);
    }

    public Aliases getAliases() {
        return aliases;
    }

    public void setAliases(Aliases aliases) {
        this.aliases = aliases;
    }
}
