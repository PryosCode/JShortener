package net.pryoscode.jshortener.log;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogHandler extends Handler {

    @Override
    public void publish(LogRecord record) {
        String out = "[" + record.getLevel().getName().toUpperCase() + "] " + record.getMessage();
        System.out.println(out);
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}

}