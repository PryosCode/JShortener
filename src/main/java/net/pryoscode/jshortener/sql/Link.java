package net.pryoscode.jshortener.sql;

public class Link {

    private int id;
    private String url;

    public Link(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}