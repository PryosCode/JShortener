package net.pryoscode.jshortener.sql.entities;

import com.sun.net.httpserver.HttpExchange;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Click {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private String userAgent;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    private Link link;

    private Click() {}

    public Click(Link link, HttpExchange request) {
        this.link = link;
        ip = request.getRequestHeaders().getFirst("X-Forwarded-For");
        ip = ip == null ? request.getRemoteAddress().getHostString() : ip.split(",")[0];
        ip = ip.trim();
        userAgent = request.getRequestHeaders().getFirst("User-agent").trim();
        timestamp = new Date();
    }

    public Link getLink() {
        return link;
    }

}