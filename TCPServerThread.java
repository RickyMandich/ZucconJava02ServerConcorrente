import java.io.*;
import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPServerThread implements Runnable {
    private Date date;
    private Socket s1; // Client socket
    private TCPServerMain tcpServerMain; // Reference to the main server

    public TCPServerThread(Socket socket, TCPServerMain tcpServerMain) {
        this.s1 = socket;
        this.tcpServerMain = tcpServerMain;
        this.date = new Date();
    }

    @Override
    public void run() {
        try {
            int clientNumber = TCPServerMain.clientNumber;
            TCPServerMain.clientNumber++;

            System.out.println("Client "+ clientNumber +" connesso");

            // Creazione degli stream di comunicazione
            OutputStream s1out = s1.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s1out));
            InputStream in = s1.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // Invio messaggio di benvenuto
            bw.write("Benvenuto sul server ;)\n");
            bw.flush();

            while (true) {
                System.out.println("Server in attesa di richiesta...");
                String s = br.readLine();  // Legge il messaggio del client
                if (s == null) break;  // Se il client chiude la connessione

                System.out.println("Richiesta dal client " + clientNumber + ": " + s);

                if (!s.equals("termina connessione")) {
                    date.setTime(System.currentTimeMillis());
                    bw.write(date.toString() + "\n");
                    bw.flush();
                } else {
                    System.out.println("Chiusura connessione con il client "+ clientNumber +".");
                    bw.close();
                    br.close();
                    s1.close();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
