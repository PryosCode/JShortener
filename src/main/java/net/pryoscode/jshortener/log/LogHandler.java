package net.pryoscode.jshortener.log;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogHandler extends Handler {

    @Override
    public void publish(LogRecord record) {
        String color = "";
        if (Level.WARNING.equals(record.getLevel()))
            color = LogColor.YELLOW;
        else if (Level.SEVERE.equals(record.getLevel()))
            color = LogColor.RED;
        System.out.println(
                color + "[" + record.getLevel().getName().toUpperCase() + "] " + record.getMessage() + LogColor.RESET);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }

}