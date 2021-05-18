package sk.timak.testprojekt.domain.searchentity.fatentity.claims;

public class Mainsnak {
    private String snaketype = "value";
    private String property;
    private DataValue datavalue;
    private String datatype = "quantity";

    public Mainsnak() {
    }

    public Mainsnak(String property, DataValue datavalue) {
        this.property = property;
        this.datavalue = datavalue;
    }

    public String getSnaketype() {
        return snaketype;
    }

    public void setSnaketype(String snaketype) {
        this.snaketype = snaketype;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public DataValue getDatavalue() {
        return datavalue;
    }

    public void setDatavalue(DataValue datavalue) {
        this.datavalue = datavalue;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
}
