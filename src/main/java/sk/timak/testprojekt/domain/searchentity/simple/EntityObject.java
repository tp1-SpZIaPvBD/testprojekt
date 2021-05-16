package sk.timak.testprojekt.domain.searchentity.simple;

import java.util.Date;

public class EntityObject {
    private Integer pageid = 1;
    private Integer ns = 0;
    private String title;
    private Integer lastrevid = 0;
    private Date modified = new Date();
    private String type;
    private String id;

    public EntityObject() {
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLastrevid() {
        return lastrevid;
    }

    public void setLastrevid(Integer lastrevid) {
        this.lastrevid = lastrevid;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
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
}
