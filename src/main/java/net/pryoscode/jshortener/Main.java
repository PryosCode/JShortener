package net.pryoscode.jshortener;

import net.pryoscode.jshortener.web.WebServer;

public class Main {

    public static void main(String[] args) throws Exception {
        WebServer server = new WebServer(80);
        server.start();
    }

}