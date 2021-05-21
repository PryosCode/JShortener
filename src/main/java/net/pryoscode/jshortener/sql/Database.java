package net.pryoscode.jshortener.sql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import net.pryoscode.jshortener.Config;
import net.pryoscode.jshortener.sql.entities.Click;
import net.pryoscode.jshortener.sql.entities.Link;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private final EntityManager manager;

    public Database() {
        Map<String, String> properties = new HashMap<>();
        if (Config.getDbType().equalsIgnoreCase("mysql")) {
            properties.put("jakarta.persistence.jdbc.url", "jdbc:mysql://" + Config.getDbHost() + ":" + Config.getDbPort() + "/" + Config.getDbName());
            properties.put("jakarta.persistence.jdbc.user", Config.getDbUser());
            properties.put("jakarta.persistence.jdbc.password", Config.getDbPassword());
            manager = Persistence.createEntityManagerFactory("MySQL", properties).createEntityManager();
        } else {
            properties.put("jakarta.persistence.jdbc.url", "jdbc:sqlite:" + Config.getDbName() + ".db");
            manager = Persistence.createEntityManagerFactory("SQLite", properties).createEntityManager();
        }
    }

    public void addLink(Link link) {
        manager.getTransaction().begin();
        manager.persist(link);
        manager.getTransaction().commit();
    }

    public void removeLink(Link link) {
        manager.getTransaction().begin();
        manager.remove(link);
        manager.getTransaction().commit();
    }

    public void addClick(Click click) {
        manager.getTransaction().begin();
        manager.persist(click);
        manager.getTransaction().commit();
        manager.refresh(click.getLink());
    }

    public Link getLinkBySlug(String slug) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Link> criteria = builder.createQuery(Link.class);
        Root<Link> root = criteria.from(Link.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("slug"), slug));
        TypedQuery<Link> typed = manager.createQuery(criteria);
        try {
            return typed.getSingleResult();
        } catch (Exception ignored) {}
        return null;
    }

    public List<Link> getLinks() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Link> criteria = builder.createQuery(Link.class);
        Root<Link> root = criteria.from(Link.class);
        CriteriaQuery<Link> query = criteria.select(root);
        TypedQuery<Link> typed = manager.createQuery(query);
        return typed.getResultList();
    }

}