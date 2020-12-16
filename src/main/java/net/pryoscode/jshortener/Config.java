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
        webPort = getEnvInt("WEB_PORT", 80);
        webStatus = getEnvInt("WEB_STATUS", 302);
        webRoot = getEnvString("WEB_ROOT", "https://github.com/PryosCode/JShortener");
        web404 = getEnvString("WEB_404", "https://github.com/PryosCode/JShortener");

        dbHost = getEnvString("DB_HOST", "127.0.0.1");
        dbPort = getEnvInt("DB_PORT", 3306);
        dbName = getEnvString("DB_NAME", "jshortener");
        dbUser = getEnvString("DB_USER", "root");
        dbPassword = getEnvString("DB_PASSWORD", "");
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

    private String getEnvString(String name, String def) {
        String term = (PREFIX + "_" + name).toUpperCase();

        String env = System.getenv(term);
        if(env != null)
            return env;

        String prop = System.getProperty(term);
        if(prop != null)
            return prop;

        return def;
    }

    private int getEnvInt(String name, int def) {
        String env = getEnvString(name, String.valueOf(def));
        return env == null ? def : Integer.parseInt(env);
    }

}