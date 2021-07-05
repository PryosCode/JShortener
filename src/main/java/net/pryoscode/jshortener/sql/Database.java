package net.pryoscode.jshortener.sql;

import net.pryoscode.jshortener.Config;
import net.pryoscode.jshortener.sql.entities.Click;
import net.pryoscode.jshortener.sql.entities.Link;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Database {

    private final EntityManager manager;

    public Database() {
        Map<String, String> properties = new HashMap<>();
        if (Config.dbType.equalsIgnoreCase("mysql")) {
            properties.put("hibernate.connection.url", "jdbc:mysql://" + Config.dbHost + ":" + Config.dbPort + "/" + Config.dbName);
            properties.put("hibernate.connection.username", Config.dbUser);
            properties.put("hibernate.connection.password", Config.dbPassword);
            manager = Persistence.createEntityManagerFactory("mysql", properties).createEntityManager();
        } else {
            properties.put("hibernate.connection.url", "jdbc:sqlite:" + Config.dbName + ".db");
            manager = Persistence.createEntityManagerFactory("sqlite", properties).createEntityManager();
        }
    }

    public void persistLink(Link link) {
        manager.getTransaction().begin();
        manager.persist(link);
        manager.getTransaction().commit();
    }

    public void removeLink(Link link) {
        manager.getTransaction().begin();
        manager.remove(link);
        manager.getTransaction().commit();
    }

    public void persistClick(Click click) {
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