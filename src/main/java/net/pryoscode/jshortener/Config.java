package net.pryoscode.jshortener;

public class Config {

    private static final String PREFIX = "JS";

    private String dbHost = "127.0.0.1";
    private int dbPort = 3306;
    private String dbName = "jshortener";
    private String dbUser = "root";
    private String dbPassword = "";

    public Config() {
        dbPassword = "0123456789";
    }

    public String getDbHost() {
        return dbHost;
    }

    public int getDbPort() {
        return dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    /*
    private <T> T getConfig(Class<T> type, String name) {
        return type.cast(System.getenv((PREFIX + "_" + name).toUpperCase()));
    }
    */

}