package net.pryoscode.jshortener.sql.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

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

    public Link() {}

    public Link(String slug, String url) {
        this.slug = slug;
        this.url = url;
        created = new Date();
    }

    public String getSlug() {
        return slug;
    }

    public String getUrl() {
        return url;
    }

}