package net.pryoscode.jshortener;

public class Config {

    public static final String PREFIX = "JS";

    public static final int shortLength = getEnvInt("SHORT_LENGTH", 5);

    public static final int webPort = getEnvInt("WEB_PORT", 80);
    public static final int webStatus = getEnvInt("WEB_STATUS", 302);
    public static final String webRoot = getEnvString("WEB_ROOT", "https://github.com/PryosCode/JShortener");
    public static final String web404 = getEnvString("WEB_404", "");

    public static final String dbType = getEnvString("DB_TYPE", "sqlite");
    public static final String dbHost = getEnvString("DB_HOST", "127.0.0.1");
    public static final int dbPort = getEnvInt("DB_PORT", 3306);
    public static final String dbName = getEnvString("DB_NAME", "jshortener");
    public static final String dbUser = getEnvString("DB_USER", "root");
    public static final String dbPassword = getEnvString("DB_PASSWORD", "");

    private Config() {}

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