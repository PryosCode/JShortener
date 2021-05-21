package net.pryoscode.jshortener;

public class Config {

    private static final String PREFIX = "JS";

    private static final int shortLength = getEnvInt("SHORT_LENGTH", 5);

    private static final int webPort = getEnvInt("WEB_PORT", 80);
    private static final int webStatus = getEnvInt("WEB_STATUS", 302);
    private static final String webRoot = getEnvString("WEB_ROOT", "https://github.com/PryosCode/JShortener");
    private static final String web404 = getEnvString("WEB_404", "");

    private static final String dbType = getEnvString("DB_TYPE", "sqlite");
    private static final String dbHost = getEnvString("DB_HOST", "127.0.0.1");
    private static final int dbPort = getEnvInt("DB_PORT", 3306);
    private static final String dbName = getEnvString("DB_NAME", "jshortener");
    private static final String dbUser = getEnvString("DB_USER", "root");
    private static final String dbPassword = getEnvString("DB_PASSWORD", "");

    private static final int vmThreads = getEnvInt("VM_THREADS", 3);

    private Config() {}

    public static int getShortLength() {
        return shortLength;
    }

    public static int getWebPort() {
        return webPort;
    }

    public static int getWebStatus() {
        return webStatus;
    }

    public static String getWebRoot() {
        return webRoot;
    }

    public static String getWeb404() {
        return web404;
    }

    public static String getDbType() {
        return dbType;
    }

    public static String getDbHost() {
        return dbHost;
    }

    public static int getDbPort() {
        return dbPort;
    }

    public static String getDbName() {
        return dbName;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    public static int getVmThreads() {
        return vmThreads;
    }

    private static String getEnvString(String name, String def) {
        String term = (PREFIX + "_" + name).toUpperCase();

        String env = System.getenv(term);
        if (env != null)
            return env;

        String prop = System.getProperty(term);
        if (prop != null)
            return prop;

        return def;
    }

    private static int getEnvInt(String name, int def) {
        String env = getEnvString(name, String.valueOf(def));
        return env == null ? def : Integer.parseInt(env);
    }

}