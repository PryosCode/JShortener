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
        server.createContext("/", request -> new Thread(() -> {
            try {
                String[] uri = request.getRequestURI().getPath().split("/");
                if(uri.length > 0) {
                    String slug = URLEncoder.encode(uri[1], StandardCharsets.UTF_8.toString());
                    WebClient client = new WebClient(request.getRemoteAddress(), request.getRequestHeaders());

                    Link link = database.getLink(slug);
                    if (link == null) {
                        request.getResponseHeaders().add("Location", config.getWeb404());
                    } else {
                        database.addClick(link.getId(), client);
                        Log.info(slug + " -> " + link.getUrl());
                        request.getResponseHeaders().add("Location", link.getUrl());
                    }
                } else {
                    request.getResponseHeaders().add("Location", config.getWebRoot());
                }
                request.sendResponseHeaders(config.getWebStatus(), 0);
                request.close();
            } catch (Exception e) {
                Log.severe(e);
            }
            Thread.currentThread().stop();
        }).start());
    }

    public void start() {
        server.start();
    }

}