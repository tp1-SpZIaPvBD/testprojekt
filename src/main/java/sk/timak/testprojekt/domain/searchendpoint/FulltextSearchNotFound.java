package sk.timak.testprojekt.domain.searchendpoint;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({ "searchinfo", "search", "success" })
public class FulltextSearchNotFound {
    private Searchinfo searchinfo;
    private List<Search> search = new ArrayList<>();
    private Integer success;

    public FulltextSearchNotFound() {
    }

    public FulltextSearchNotFound(String searchinfo, Integer success) {
        this.searchinfo = new Searchinfo(searchinfo);
        this.success = success;
    }

    public Searchinfo getSearchinfo() {
        return searchinfo;
    }

    public void setSearchinfo(Searchinfo searchinfo) {
        this.searchinfo = searchinfo;
    }

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
