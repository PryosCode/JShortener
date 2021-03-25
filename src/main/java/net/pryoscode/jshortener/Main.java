package net.pryoscode.jshortener;

import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;

public class Main {

    public static void main(String[] args) {
        try {
            Log.setup();
            Log.warning("CommandManager is currently under construction.");
            Config config = new Config();
            Database database = new Database(config);
            Server server = new Server(database, config);
            server.start();
        } catch (Exception e) {
            Log.severe(e);
        }
    }

}