package net.pryoscode.jshortener.web;

import com.sun.net.httpserver.Headers;
import java.net.InetSocketAddress;

public class WebClient {

    private String ip;
    private String userAgent;

    public WebClient(InetSocketAddress socket, Headers headers) {
        this.ip = socket.getAddress().toString().substring(1);
        this.userAgent = headers.getFirst("User-agent");
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

}