package net.pryoscode.jshortener.web;

import com.sun.net.httpserver.HttpServer;
import net.pryoscode.jshortener.Config;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;
import net.pryoscode.jshortener.sql.entities.Click;
import net.pryoscode.jshortener.sql.entities.Link;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {

    private final HttpServer server;
    private final ExecutorService executor;

    public WebServer(Database database, Config config) throws IOException {
        server = HttpServer.create(new InetSocketAddress(config.getWebPort()), 0);
        executor = Executors.newFixedThreadPool(config.getVmThreads());
        server.createContext("/", request -> executor.submit(() -> {
            try {
                String[] uri = request.getRequestURI().getPath().split("/");
                if (uri.length > 0) {
                    String slug = URLEncoder.encode(uri[1], StandardCharsets.UTF_8.toString());

                    Link link = database.getLinkBySlug(slug);
                    if (link == null) {
                        request.getResponseHeaders().add("Location", config.getWeb404());
                    } else {
                        database.addClick(new Click(link, request));
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
        }));
    }

    public void start() {
        server.start();
    }

}