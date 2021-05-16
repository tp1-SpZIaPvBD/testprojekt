package sk.timak.testprojekt.domain.searchendpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({ "searchinfo", "search", "search-continue", "success" })
public class FulltextSearchResponse {
    private Searchinfo searchinfo;
    private List<Search> search = new ArrayList<>();
    @JsonProperty("search-continue")
    private Integer searchcontinue;
    private Integer success;

    public FulltextSearchResponse(String searchinfo) {
        this.searchinfo = new Searchinfo(searchinfo);
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

    public Integer getSearchcontinue() {
        return searchcontinue;
    }

    @JsonProperty("search-continue")
    public void setSearchcontinue(Integer searchcontinue) {
        this.searchcontinue = searchcontinue;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
