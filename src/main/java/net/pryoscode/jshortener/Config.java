package net.pryoscode.jshortener;

public class Config {

    private static final String PREFIX = "JS";

    private final int webPort;
    private final int webStatus;
    private final String webRoot;
    private final String web404;
    private final String webForwardedProto;

    private final String dbHost;
    private final int dbPort;
    private final String dbName;
    private final String dbUser;
    private final String dbPassword;

    public Config() {
        webPort = getEnv("WEB_PORT", 80);
        webStatus = getEnv("WEB_STATUS", 302);
        webRoot = getEnv("WEB_ROOT", "https://github.com/PryosCode/JShortener");
        web404 = getEnv("WEB_404", "https://github.com/PryosCode/JShortener");
        webForwardedProto = getEnv("WEB_FORWARDED_PROTO", "http");

        dbHost = getEnv("DB_HOST", "127.0.0.1");
        dbPort = getEnv("DB_PORT", 3306);
        dbName = getEnv("DB_NAME", "jshortener");
        dbUser = getEnv("DB_USER", "root");
        dbPassword = getEnv("DB_PASSWORD", "");
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

    public String getWebForwardedProto() {
        return webForwardedProto;
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

    private <T> T getEnv(String name, T def) {
        String term = (PREFIX + "_" + name).toUpperCase();

        T env = (T) System.getenv(term);
        if(env != null)
            return env;

        T prop = (T) System.getProperty(term);
        if(prop != null)
            return prop;

        return def;
    }

}