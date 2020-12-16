package net.pryoscode.jshortener.web;

import com.sun.net.httpserver.HttpServer;
import net.pryoscode.jshortener.Config;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;
import net.pryoscode.jshortener.sql.Link;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class WebServer {

    private final HttpServer server;

    public WebServer(final Database database, final Config config) throws IOException {
        server = HttpServer.create(new InetSocketAddress(config.getWebPort()), 0);
        server.createContext("/", request -> {
            try {
                String slug = URLEncoder.encode(request.getRequestURI().getPath().split("/")[1], StandardCharsets.UTF_8.toString());
                WebClient client = new WebClient(request.getRemoteAddress(), request.getRequestHeaders());

                Link link = database.getLink(slug);
                database.addClick(link.getId(), client);

                request.getResponseHeaders().add("Location", link.getUrl());
                request.sendResponseHeaders(config.getWebRedirect(), 0);
            } catch (Exception e) {
                Log.severe(e);
            }
        });
    }

    public void start() {
        server.start();
    }

}