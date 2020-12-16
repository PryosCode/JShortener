package net.pryoscode.jshortener.sql;

import net.pryoscode.jshortener.Config;
import net.pryoscode.jshortener.web.WebClient;
import java.sql.*;

public class Database {

    private final Connection connection;

    public Database(final Config config) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + config.getDbHost() + ":" + config.getDbPort() + "/" + config.getDbName(), config.getDbUser(), config.getDbPassword());
    }

    public void setup() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS LINKS (" +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "SLUG TEXT NOT NULL," +
                "URL TEXT NOT NULL" +
                ")");
        statement.execute("CREATE TABLE IF NOT EXISTS CLICKS (" +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "LINK_ID INTEGER NOT NULL," +
                "IP VARCHAR(255) NOT NULL," +
                "USER_AGENT TEXT NOT NULL" +
                ")");
        statement.close();
    }

    public Link getLink(String slug) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT ID, URL FROM LINKS WHERE SLUG = ?");
        statement.setString(1, slug);
        ResultSet result = statement.executeQuery();
        result.next();
        return new Link(result.getInt(1), result.getString(2));
    }

    public void addClick(int id, WebClient client) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO CLICKS VALUES (NULL, ?, ?, ?)");
        statement.setInt(1, id);
        statement.setString(2, client.getIp());
        statement.setString(3, client.getUserAgent());
        statement.execute();
    }

}