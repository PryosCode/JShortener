package net.pryoscode.jshortener.log;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {

    private static Logger logger;

    private Log() {}

    public static void setup() {
        LogManager.getLogManager().reset();
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.addHandler(new LogHandler());
        welcome();
    }

    private static void welcome() {
        info("       _______ __               __                      ");
        info("      / / ___// /_  ____  _____/ /____  ____  ___  _____");
        info(" __  / /\\__ \\/ __ \\/ __ \\/ ___/ __/ _ \\/ __ \\/ _ \\/ ___/");
        info("/ /_/ /___/ / / / / /_/ / /  / /_/  __/ / / /  __/ /    ");
        info("\\____//____/_/ /_/\\____/_/   \\__/\\___/_/ /_/\\___/_/     ");
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void config(String message) {
        logger.config(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void severe(Exception exception) {
        logger.severe(exception.getMessage());
        for(StackTraceElement trace : exception.getStackTrace())
            logger.severe(trace.getFileName() + "/" + trace.getClassName() + "#" + trace.getMethodName() + ":" + trace.getLineNumber());
    }

}