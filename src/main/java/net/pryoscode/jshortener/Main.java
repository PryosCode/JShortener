package net.pryoscode.jshortener;

import net.pryoscode.jshortener.cmd.CommandListener;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;
import net.pryoscode.jshortener.web.WebServer;

public class Main {

    public static void main(String[] args) {
        try {
            Log.setup();
            Database database = new Database();
            new CommandListener(database).start();
            new WebServer(database).start();
        } catch (Exception e) {
            Log.severe(e);
        }
    }

}