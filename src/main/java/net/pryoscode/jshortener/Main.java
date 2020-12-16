package net.pryoscode.jshortener;

import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;
import net.pryoscode.jshortener.web.WebServer;

public class Main {

    public static void main(String[] args) {
        try {
            Log.setup();
            Config config = new Config();
            Database database = new Database(config);
            database.setup();
            //CommandHandler cmdHandler = new CommandHandler();
            //cmdHandler.start();
            WebServer server = new WebServer(database, config);
            server.start();
        } catch (Exception e) {
            Log.severe(e);
        }
    }

}