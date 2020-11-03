package net.pryoscode.jshortener.web;

import java.io.*;
import java.net.*;

public class WebServer {

    private ServerSocket server;

    public WebServer(int port) throws IOException {
        server = new ServerSocket(port);
        while (true) {
            try {
                Socket client = server.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                String ip = ((InetSocketAddress) client.getRemoteSocketAddress()).getAddress().toString().substring(1);
                System.out.println(ip);

                writer.write("HTTP/1.1 302 Found\r\n");
                writer.write("Location: https://pryoscode.net\r\n");
                writer.write("Server: JShortener\r\n");
                writer.write("\r\n");


                writer.close();
                reader.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}