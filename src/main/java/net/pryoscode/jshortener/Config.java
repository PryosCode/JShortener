package net.pryoscode.jshortener;

public class Config {

    private static final String PREFIX = "JS";

    private final int webPort;
    private final int webStatus;
    private final String webRoot;
    private final String web404;

    private final String dbHost;
    private final int dbPort;
    private final String dbName;
    private final String dbUser;
    private final String dbPassword;

    public Config() {
        webPort = getEnv("WEB_PORT", 80, Integer.class);
        webStatus = getEnv("WEB_STATUS", 302, Integer.class);
        webRoot = getEnv("WEB_ROOT", "https://github.com/PryosCode/JShortener", String.class);
        web404 = getEnv("WEB_404", "https://github.com/PryosCode/JShortener", String.class);

        dbHost = getEnv("DB_HOST", "127.0.0.1", String.class);
        dbPort = getEnv("DB_PORT", 3306, Integer.class);
        dbName = getEnv("DB_NAME", "jshortener", String.class);
        dbUser = getEnv("DB_USER", "root", String.class);
        dbPassword = getEnv("DB_PASSWORD", "", String.class);
    }

    public int getWebPort() {
        return webPort;
    }

    public int getWebStatus() {
        return webStatus;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public String getWeb404() {
        return web404;
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

    private <T> T getEnv(String name, T def, Class<T> type) {
        T env = (T) System.getenv((PREFIX + "_" + name).toUpperCase());
        if(env == null)
            return def;
        return env;
    }

}