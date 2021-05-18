package sk.timak.testprojekt.domain.searchentity.fatentity;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FatEntityStructure {
    private String type = "item";
    private String id;
    private Labels labels;
    private Descriptions descriptions;
    private Map<String, ArrayList<Object>> claims = new LinkedHashMap<>();
    private Aliases aliases;

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

    public Map<String, ArrayList<Object>> getClaims() {
        return claims;
    }

    @JsonAnySetter
    public void setClaims(String key, Object value) {
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(value);
        this.claims.put(key, arrayList);
    }

    public Aliases getAliases() {
        return aliases;
    }

    public void setAliases(Aliases aliases) {
        this.aliases = aliases;
    }
}
