package net.pryoscode.jshortener.web;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.net.InetSocketAddress;

public class WebClient {

    private String ip;
    private String userAgent;

    public WebClient(HttpExchange request) {
        ip = request.getRequestHeaders().getFirst("X-Forwarded-For");
        if(ip == null)
            ip = request.getRemoteAddress().getHostString();
        this.userAgent = request.getRequestHeaders().getFirst("User-agent");
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

}