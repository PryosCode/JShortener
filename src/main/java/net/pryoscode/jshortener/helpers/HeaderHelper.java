package net.pryoscode.jshortener.helpers;

import java.io.BufferedReader;

public class HeaderHelper {

    private String path;
    private String userAgent;

    public HeaderHelper(BufferedReader reader) {
    }

    public String getPath() {
        return path;
    }

    public String getUserAgent() {
        return userAgent;
    }

}