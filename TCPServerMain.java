import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPServerMain {
    Date date;
    private int port;
    private InetAddress ip;
    private ServerSocket server;
    public static int clientNumber = 1;

    public TCPServerMain(InetAddress ip, int port) throws IOException {
        this.date = new Date();
        this.port = port;
        this.ip = ip;
        if(!startServer())
            System.out.println("Errore creazione server");
    }

    private boolean startServer(){
        try {
            this.server = new ServerSocket(this.port);
        } catch (IOException e) {
            return false;
        }

        System.out.println("Server creato con successo");
        return true;
    }

    public void runServer() {
        while (true) {
            System.out.println("Server in attesa di connessione...");
            try {
                Socket s1;

                while (true) {
                    s1 = server.accept();

                    TCPServerThread tcpServerThread = new TCPServerThread(s1, this);
                    Thread thread = new Thread(tcpServerThread);
                    thread.start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    //SETTER E GETTER
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public int getClientNumber(){
        clientNumber++;
        return clientNumber;
    }
    //FINE SETTER e GETTER

    public static void main(String[] args) throws IOException {

        TCPServerMain s = new TCPServerMain(InetAddress.getByName("127.0.0.1"), 20000);

        s.runServer();
    }
}
