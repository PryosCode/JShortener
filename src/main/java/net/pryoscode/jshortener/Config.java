package net.pryoscode.jshortener;

public class Config {

    public Config() {
        String port = System.getenv("JSHORTENER_PORT");
    }

}