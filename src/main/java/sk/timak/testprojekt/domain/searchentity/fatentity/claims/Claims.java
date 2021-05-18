package sk.timak.testprojekt.domain.searchentity.fatentity.claims;

public class Claims {
    private Mainsnak mainsnak;
    private String type = "statement";

    public Claims() {
    }

    public Claims(Mainsnak mainsnak) {
        this.mainsnak = mainsnak;
    }

    public Mainsnak getMainsnak() {
        return mainsnak;
    }

    public void setMainsnak(Mainsnak mainsnak) {
        this.mainsnak = mainsnak;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
