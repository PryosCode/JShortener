package net.pryoscode.jshortener.web;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class WebServer {

    private HttpServer server;

    public WebServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", exchange -> {
            exchange.getResponseHeaders().add("Location", "https://pryoscode.net");
            exchange.sendResponseHeaders(302, 0);
            System.out.println(URLEncoder.encode(exchange.getRequestURI().getPath().split("/")[1], StandardCharsets.UTF_8));
        });
    }

    public void start() {
        server.start();
    }

}