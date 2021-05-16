package sk.timak.testprojekt.domain.searchendpoint;

public class Search {
    private String id;
    private String title;
    private Integer pageid;
    private String repository = "timak";
    private String url;
    private String concepturi;
    private String label;
    private String description;
    private Match match;

    public Search() {
    }

    public Search(String id, String title, Integer pageid, String url, String concepturi, String label, String description, Match match) {
        this.id = id;
        this.title = title;
        this.pageid = pageid;
        this.url = url;
        this.concepturi = concepturi;
        this.label = label;
        this.description = description;
        this.match = match;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConcepturi() {
        return concepturi;
    }

    public void setConcepturi(String concepturi) {
        this.concepturi = concepturi;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public static class SearchBuilder {
        private String id;
        private String title;
        private Integer pageid;
        private String url;
        private String concepturi;
        private String label;
        private String description;
        private Match match;

        public SearchBuilder (){
        }

        public SearchBuilder setId(String id){
            this.id = id;
            return this;
        }

        public SearchBuilder setTitle(String title){
            this.title = title;
            return this;
        }

        public SearchBuilder setPageId(Integer pageId){
            this.pageid = pageId;
            return this;
        }

        public SearchBuilder setUrl(String url){
            this.url = url;
            return this;
        }

        public SearchBuilder setConceptUri(String concepturi){
            this.concepturi = concepturi;
            return this;
        }

        public SearchBuilder setLabel(String label){
            this.label = label;
            return this;
        }

        public SearchBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public SearchBuilder setMatch(Match match){
            this.match = match;
            return this;
        }

        public Search build() {
            return new Search(id,title,pageid,url,concepturi,label,description,match);
        }
    }
}
