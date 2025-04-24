package org.example;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {

        int port = 8080;
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        SimpleServer server = new SimpleServer(ip, port);
        server.runServer();
    }

}