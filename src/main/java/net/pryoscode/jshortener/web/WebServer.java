package net.pryoscode.jshortener.web;

import com.sun.net.httpserver.HttpServer;
import net.pryoscode.jshortener.Config;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;
import net.pryoscode.jshortener.sql.entities.Click;
import net.pryoscode.jshortener.sql.entities.Link;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {

    private static String notFound;
    private final HttpServer server;
    private final ExecutorService executor;

    public WebServer(Database database) throws IOException {
        server = HttpServer.create(new InetSocketAddress(Config.getWebPort()), 0);
        executor = Executors.newFixedThreadPool(Config.getVmThreads());

        if (Config.getWeb404().isEmpty()) {
            try {
                InputStream iStream = getClass().getClassLoader().getResourceAsStream("html/404.html");
                InputStreamReader isReader = new InputStreamReader(iStream);
                BufferedReader bReader = new BufferedReader(isReader);
                StringBuffer sBuffer = new StringBuffer();
                String line;
                while ((line = bReader.readLine()) != null)
                    sBuffer.append(line);
                notFound = sBuffer.toString();
                bReader.close();
                isReader.close();
                iStream.close();
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
                        if (Config.getWeb404().isEmpty()) {
                            request.sendResponseHeaders(404, 0);
                            OutputStream stream = request.getResponseBody();
                            stream.write(notFound.getBytes());
                            stream.close();
                        } else {
                            request.getResponseHeaders().add("Location", Config.getWeb404());
                            request.sendResponseHeaders(Config.getWebStatus(), 0);
                        }
                    } else {
                        database.addClick(new Click(link, request));
                        request.getResponseHeaders().add("Location", link.getUrl());
                        request.sendResponseHeaders(Config.getWebStatus(), 0);
                    }
                } else {
                    request.getResponseHeaders().add("Location", Config.getWebRoot());
                    request.sendResponseHeaders(Config.getWebStatus(), 0);
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