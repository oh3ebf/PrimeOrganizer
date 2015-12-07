/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh3ebf.demo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kimkr
 */
@Entity
@NamedQueries({
    @NamedQuery(name = NoteBookItem.ALL, query = "SELECT n FROM NoteBookItem n "),
    @NamedQuery(name = NoteBookItem.TOTAL, query = "SELECT COUNT(n) FROM NoteBookItem n")
})
public class NoteBookItem extends BaseEntity implements Serializable {
    public final static String ALL = "NoteBookItem.populateUsers";
    public final static String TOTAL = "NoteBookItem.countUsersTotal";
    
    private String header;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    public NoteBookItem() {
        header = "";
        content = "";
        createdAt = null;
        updatedAt = null;
    }
    
    public NoteBookItem(String header, String content, Date create, Date update) {
        this.header = header;
        this.content = content;
        this.createdAt = create;
        this.updatedAt = update;
    }
    
    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
