package net.pryoscode.jshortener.web;

import com.sun.net.httpserver.HttpServer;
import net.pryoscode.jshortener.Config;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;
import net.pryoscode.jshortener.sql.entities.Click;
import net.pryoscode.jshortener.sql.entities.Link;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {

    private static String notFound;
    private final HttpServer server;
    private final ExecutorService executor;

    public WebServer(Database database) throws IOException {
        server = HttpServer.create(new InetSocketAddress(Config.webPort), 0);
        executor = Executors.newCachedThreadPool();
        server.setExecutor(executor);

        if (Config.web404.isEmpty()) {
            try {
                InputStream stream = getClass().getClassLoader().getResourceAsStream("html/404.html");
                Scanner scanner = new Scanner(stream).useDelimiter("\\Z");
                notFound = scanner.next();
                scanner.close();
                stream.close();
            } catch (Exception e) {
                Log.severe(e);
            }
        }

        server.createContext("/", request -> executor.submit(() -> {
            try {
                String[] uri = request.getRequestURI().getPath().split("/");
                if (uri.length > 0) {
                    String slug = URLEncoder.encode(uri[1], StandardCharsets.UTF_8.toString());

                    Link link = database.getLinkBySlug(slug);
                    if (link == null) {
                        if (Config.web404.isEmpty()) {
                            request.sendResponseHeaders(404, 0);
                            OutputStream stream = request.getResponseBody();
                            stream.write(notFound.getBytes());
                            stream.close();
                        } else {
                            request.getResponseHeaders().add("Location", Config.web404);
                            request.sendResponseHeaders(Config.webStatus, 0);
                        }
                    } else {
                        database.persistClick(new Click(link, request));
                        request.getResponseHeaders().add("Location", link.getUrl());
                        request.sendResponseHeaders(Config.webStatus, 0);
                    }
                } else {
                    request.getResponseHeaders().add("Location", Config.webRoot);
                    request.sendResponseHeaders(Config.webStatus, 0);
                }
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