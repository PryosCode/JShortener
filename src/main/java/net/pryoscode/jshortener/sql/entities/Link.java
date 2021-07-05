package net.pryoscode.jshortener.sql.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;

    private String url;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany(mappedBy = "link")
    private List<Click> clicks;

    private Link() {}

    public Link(String slug, String url) {
        this.slug = slug;
        this.url = url;
        clicks = new ArrayList<>();
        created = new Date();
    }

    public String getSlug() {
        return slug;
    }

    public String getUrl() {
        return url;
    }

    public List<Click> getClicks() {
        return clicks;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}