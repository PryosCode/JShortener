package net.pryoscode.jshortener;

import com.sun.net.httpserver.HttpServer;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;
import net.pryoscode.jshortener.sql.entities.Click;
import net.pryoscode.jshortener.sql.entities.Link;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final HttpServer server;
    private final ExecutorService executor;

    public Server(Database database, Config config) throws IOException {
        server = HttpServer.create(new InetSocketAddress(config.getWebPort()), 0);
        executor = Executors.newFixedThreadPool(config.getVmThreads());
        server.createContext("/", request -> executor.submit(() -> {
            try {
                String[] uri = request.getRequestURI().getPath().split("/");
                if(uri.length > 0) {
                    String slug = URLEncoder.encode(uri[1], StandardCharsets.UTF_8.toString());

                    Link link = database.getLink(slug);
                    if (link == null) {
                        request.getResponseHeaders().add("Location", config.getWeb404());
                    } else {
                        database.addClick(new Click(link, request));
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
        }));
    }

    public void start() {
        server.start();
    }

}