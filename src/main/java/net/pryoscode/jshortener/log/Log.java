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