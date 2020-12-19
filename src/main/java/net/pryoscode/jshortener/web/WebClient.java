package net.pryoscode.jshortener.web;

import com.sun.net.httpserver.HttpExchange;

public class WebClient {

    private String ip;
    private String userAgent;

    public WebClient(HttpExchange request) {
        ip = request.getRequestHeaders().getFirst("X-Forwarded-For");
        ip = ip == null ? request.getRemoteAddress().getHostString() : ip.split(",")[0];
        ip = ip.trim();
        userAgent = request.getRequestHeaders().getFirst("User-agent").trim();
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

}