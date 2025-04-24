package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        try {
            Socket cli = new Socket("127.0.0.1", 8080);
            System.out.println("prima di inputstream");
            InputStream in = cli.getInputStream();
            System.out.println("prima di bufferreader");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            System.out.println("risposta del server: "+ br.readLine());

            br.close();
            cli.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
